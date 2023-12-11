package com.commercial.backend.security.exception;

public class AlreadyExist5AttemptsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: not existing 5 attempts, but actual: exists 5 attempts";
    }
}
