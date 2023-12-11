package com.commercial.backend.exception.not.valid;

public class NotValidException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: correct data, but actual: incorrect data";
    }
}
