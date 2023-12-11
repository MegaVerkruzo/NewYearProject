package com.commercial.backend.exception.user.exists;

import lombok.Getter;

@Getter
public class UserExistsResponse {
    private final String error = "userExists";
}
