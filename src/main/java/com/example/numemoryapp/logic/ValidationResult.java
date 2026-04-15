package com.example.numemoryapp.logic;

import java.util.Objects;

public record ValidationResult(
        GameStatus previousStatus,
        GameStatus nextStatus,
        boolean correct,
        ValidationReason reason,
        int progress
) {
    public ValidationResult {
        Objects.requireNonNull(previousStatus, "previousStatus must not be null");
        Objects.requireNonNull(nextStatus, "nextStatus must not be null");
        Objects.requireNonNull(reason, "reason must not be null");
        if (progress < 0) {
            throw new IllegalArgumentException("progress must be >= 0");
        }
    }
}
