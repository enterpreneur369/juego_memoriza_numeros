package com.example.numemoryapp.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineRulesTest {

    @Test
    void shouldAdvanceProgressOnCorrectAttempt() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1, 2, 3));

        ValidationResult result = engine.evaluateAttempt(session, 1);

        assertTrue(result.correct());
        assertEquals(ValidationReason.CORRECT, result.reason());
        assertEquals(1, result.progress());
        assertEquals(GameStatus.IN_PROGRESS, session.status());
    }

    @Test
    void shouldEndInDefeatOnIncorrectAttempt() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1, 2, 3));

        ValidationResult result = engine.evaluateAttempt(session, 3);

        assertFalse(result.correct());
        assertEquals(ValidationReason.INCORRECT, result.reason());
        assertEquals(GameStatus.DEFEAT, session.status());
    }
}
