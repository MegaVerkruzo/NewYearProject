package com.commercial.backend.exception.no.user;

import lombok.Getter;

@Getter
public class NotFoundUserResponse {
    private final String error = "noUser";
}
