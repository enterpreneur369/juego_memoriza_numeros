package com.example.numemoryapp.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSessionInvalidTransitionTest {

    @Test
    void shouldRejectInvalidDirectTransitionFromNewToVictory() {
        GameSession session = new GameSession(List.of(1, 2, 3));

        ValidationResult result = session.transitionTo(GameStatus.VICTORY);

        assertEquals(GameStatus.NEW, result.previousStatus());
        assertEquals(GameStatus.NEW, result.nextStatus());
        assertEquals(ValidationReason.INVALID_TRANSITION, result.reason());
    }

    @Test
    void shouldRejectTransitionAfterTerminalState() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1));
        engine.evaluateAttempt(session, 1);

        ValidationResult result = session.transitionTo(GameStatus.IN_PROGRESS);

        assertEquals(GameStatus.VICTORY, result.previousStatus());
        assertEquals(GameStatus.VICTORY, result.nextStatus());
        assertEquals(ValidationReason.INVALID_TRANSITION, result.reason());
    }
}
