package com.example.numemoryapp.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineEdgeCaseTest {

    @Test
    void shouldFailDeterministicallyWhenSequenceIsEmpty() {
        GameSession session = new GameSession(List.of());

        ValidationResult start = session.start();
        ValidationResult attempt = session.registerAttempt(1);

        assertEquals(GameStatus.DEFEAT, start.nextStatus());
        assertEquals(ValidationReason.EMPTY_SEQUENCE, start.reason());
        assertEquals(ValidationReason.TERMINAL_STATE, attempt.reason());
    }

    @Test
    void shouldReturnOutOfRangeWithoutCorruptingState() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1, 2, 3));

        ValidationResult outOfRange = engine.evaluateAttempt(session, 99);

        assertEquals(ValidationReason.OUT_OF_RANGE, outOfRange.reason());
        assertEquals(GameStatus.IN_PROGRESS, session.status());
        assertEquals(0, session.progress());
    }

    @Test
    void shouldEvaluateDuplicatesPositionallyAndConsistently() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1, 1, 2));

        ValidationResult first = engine.evaluateAttempt(session, 1);
        ValidationResult second = engine.evaluateAttempt(session, 1);
        ValidationResult third = engine.evaluateAttempt(session, 2);

        assertEquals(ValidationReason.CORRECT, first.reason());
        assertEquals(ValidationReason.CORRECT, second.reason());
        assertEquals(GameStatus.VICTORY, third.nextStatus());
    }
}
