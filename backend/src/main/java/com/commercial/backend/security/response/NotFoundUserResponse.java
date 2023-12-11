package com.commercial.backend.security.response;

import com.commercial.backend.security.ApiException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.security.ApiException.notRegistered;

@Getter
public class NotFoundUserResponse {
    @Schema(example = "notRegistered")
    private final ApiException exception = notRegistered;
}
