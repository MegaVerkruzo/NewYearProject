package com.commercial.backend.exception.bad.request;

import lombok.Getter;

@Getter
public class BadRequestResponse {
    private final BadRequest exception = BadRequest.badRequest;
}
