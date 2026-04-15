package com.example.numemoryapp.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class GameSession {

    private final List<Integer> targetSequence;
    private GameStatus status;
    private int progress;

    public GameSession(List<Integer> targetSequence) {
        Objects.requireNonNull(targetSequence, "targetSequence must not be null");
        this.targetSequence = List.copyOf(new ArrayList<>(targetSequence));
        this.status = GameStatus.NEW;
        this.progress = 0;
    }

    public GameStatus status() {
        return status;
    }

    public int progress() {
        return progress;
    }

    public List<Integer> targetSequence() {
        return targetSequence;
    }

    public ValidationResult start() {
        if (status != GameStatus.NEW) {
            return unchanged(ValidationReason.INVALID_TRANSITION, false);
        }

        if (targetSequence.isEmpty()) {
            return setStatus(GameStatus.DEFEAT, ValidationReason.EMPTY_SEQUENCE, false);
        }

        return setStatus(GameStatus.IN_PROGRESS, ValidationReason.GAME_STARTED, true);
    }

    public ValidationResult registerAttempt(int selectedValue) {
        if (isTerminal(status)) {
            return unchanged(ValidationReason.TERMINAL_STATE, false);
        }

        if (status != GameStatus.IN_PROGRESS) {
            return unchanged(ValidationReason.INVALID_TRANSITION, false);
        }

        if (!targetSequence.contains(selectedValue)) {
            return unchanged(ValidationReason.OUT_OF_RANGE, false);
        }

        int expected = targetSequence.get(progress);
        if (selectedValue == expected) {
            GameStatus previous = status;
            progress++;
            if (progress == targetSequence.size()) {
                status = GameStatus.VICTORY;
            }
            return new ValidationResult(previous, status, true, ValidationReason.CORRECT, progress);
        }

        return setStatus(GameStatus.DEFEAT, ValidationReason.INCORRECT, false);
    }

    public ValidationResult transitionTo(GameStatus nextStatus) {
        Objects.requireNonNull(nextStatus, "nextStatus must not be null");

        if (!canTransitionTo(nextStatus)) {
            return unchanged(ValidationReason.INVALID_TRANSITION, false);
        }

        if (status == nextStatus) {
            return unchanged(ValidationReason.CORRECT, true);
        }

        return setStatus(nextStatus, ValidationReason.CORRECT, true);
    }

    public boolean canTransitionTo(GameStatus nextStatus) {
        Objects.requireNonNull(nextStatus, "nextStatus must not be null");

        return switch (status) {
            case NEW -> nextStatus == GameStatus.IN_PROGRESS;
            case IN_PROGRESS -> nextStatus == GameStatus.VICTORY || nextStatus == GameStatus.DEFEAT;
            case VICTORY, DEFEAT -> false;
        };
    }

    private ValidationResult setStatus(GameStatus nextStatus, ValidationReason reason, boolean correct) {
        GameStatus previous = status;
        status = nextStatus;
        return new ValidationResult(previous, status, correct, reason, progress);
    }

    private ValidationResult unchanged(ValidationReason reason, boolean correct) {
        return new ValidationResult(status, status, correct, reason, progress);
    }

    private boolean isTerminal(GameStatus value) {
        return value == GameStatus.VICTORY || value == GameStatus.DEFEAT;
    }
}
