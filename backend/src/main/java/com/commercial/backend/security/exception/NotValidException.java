package com.commercial.backend.security.exception;

public class NotValidException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: correct data, but actual: incorrect data";
    }
}
