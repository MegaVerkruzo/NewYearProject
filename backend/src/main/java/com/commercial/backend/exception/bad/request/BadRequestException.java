package com.commercial.backend.exception.bad.request;

public class BadRequestException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: ..., but actual: bad request";
    }
}
