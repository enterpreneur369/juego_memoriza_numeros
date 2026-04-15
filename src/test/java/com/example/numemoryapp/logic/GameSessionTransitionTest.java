package com.example.numemoryapp.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSessionTransitionTest {

    @Test
    void shouldTransitionFromNewToInProgressOnStart() {
        GameSession session = new GameSession(List.of(1, 2));

        ValidationResult result = session.start();

        assertEquals(GameStatus.NEW, result.previousStatus());
        assertEquals(GameStatus.IN_PROGRESS, result.nextStatus());
        assertEquals(ValidationReason.GAME_STARTED, result.reason());
    }

    @Test
    void shouldTransitionFromInProgressToVictoryWhenSequenceCompletes() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1, 2));

        engine.evaluateAttempt(session, 1);
        ValidationResult result = engine.evaluateAttempt(session, 2);

        assertEquals(GameStatus.IN_PROGRESS, result.previousStatus());
        assertEquals(GameStatus.VICTORY, result.nextStatus());
        assertEquals(ValidationReason.CORRECT, result.reason());
    }

    @Test
    void shouldTransitionFromInProgressToDefeatOnIncorrectAttempt() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1, 2));

        ValidationResult result = engine.evaluateAttempt(session, 9);

        assertEquals(GameStatus.IN_PROGRESS, result.previousStatus());
        assertEquals(GameStatus.IN_PROGRESS, result.nextStatus());
        assertEquals(ValidationReason.OUT_OF_RANGE, result.reason());

        ValidationResult incorrect = engine.evaluateAttempt(session, 2);
        assertEquals(GameStatus.DEFEAT, incorrect.nextStatus());
    }
}
