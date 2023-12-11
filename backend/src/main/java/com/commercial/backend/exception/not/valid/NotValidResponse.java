package com.commercial.backend.exception.not.valid;

import lombok.Getter;

@Getter
public class NotValidResponse {
    private final NotValid exception = NotValid.notValid;
}
