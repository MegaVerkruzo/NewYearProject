package com.commercial.backend.security.exception;

public class WrongSizeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: correct size, but actual: incorrect size";
    }
}
