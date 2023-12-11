package com.commercial.backend.exception.no.user;

public class NotFoundUserException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: found user, but actual: not found user";
    }
}
