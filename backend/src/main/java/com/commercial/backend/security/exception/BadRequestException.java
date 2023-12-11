package com.commercial.backend.security.exception;

public class BadRequestException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: ..., but actual: bad request";
    }
}
