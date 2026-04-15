package com.example.numemoryapp.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GameEngineTerminalStateTest {

    @Test
    void shouldKeepVictoryStateImmutableAfterAdditionalAttempts() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1));

        ValidationResult win = engine.evaluateAttempt(session, 1);
        ValidationResult postWinAttempt = engine.evaluateAttempt(session, 1);

        assertEquals(GameStatus.VICTORY, win.nextStatus());
        assertFalse(postWinAttempt.correct());
        assertEquals(ValidationReason.TERMINAL_STATE, postWinAttempt.reason());
        assertEquals(GameStatus.VICTORY, session.status());
    }

    @Test
    void shouldKeepDefeatStateImmutableAfterAdditionalAttempts() {
        GameEngine engine = new GameEngine();
        GameSession session = engine.createSession(List.of(1, 2));

        ValidationResult lose = engine.evaluateAttempt(session, 2);
        ValidationResult postLoseAttempt = engine.evaluateAttempt(session, 1);

        assertEquals(GameStatus.DEFEAT, lose.nextStatus());
        assertEquals(ValidationReason.TERMINAL_STATE, postLoseAttempt.reason());
        assertEquals(GameStatus.DEFEAT, session.status());
    }
}
