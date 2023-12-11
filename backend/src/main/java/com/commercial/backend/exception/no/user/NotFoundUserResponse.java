package com.commercial.backend.exception.no.user;

import lombok.Getter;

@Getter
public class NotFoundUserResponse {
    private final NoUser exception = NoUser.noUser;
}
