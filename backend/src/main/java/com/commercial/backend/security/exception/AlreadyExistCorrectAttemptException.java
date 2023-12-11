package com.commercial.backend.security.exception;

public class AlreadyExistCorrectAttemptException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: not existing correct attempt, but actual: exists correct attempt";
    }
}
