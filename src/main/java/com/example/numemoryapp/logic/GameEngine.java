package com.example.numemoryapp.logic;

import java.util.List;
import java.util.Objects;

public final class GameEngine {

    public GameSession createSession(List<Integer> targetSequence) {
        GameSession session = new GameSession(Objects.requireNonNull(targetSequence, "targetSequence must not be null"));
        session.start();
        return session;
    }

    public ValidationResult evaluateAttempt(GameSession session, int selectedValue) {
        Objects.requireNonNull(session, "session must not be null");
        return session.registerAttempt(selectedValue);
    }
}
