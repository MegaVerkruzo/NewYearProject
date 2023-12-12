package com.commercial.backend.security.exception;

public class NotRegisteredException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: found user, but actual: not found user";
    }
}
