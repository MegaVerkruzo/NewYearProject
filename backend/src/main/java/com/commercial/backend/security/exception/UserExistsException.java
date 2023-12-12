package com.commercial.backend.security.exception;

import com.commercial.backend.db.entities.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserExistsException extends RuntimeException {
    private final User user;

    @Override
    public String getMessage() {
        return "Expected: no found user, but actual: " + user.toString();
    }
}