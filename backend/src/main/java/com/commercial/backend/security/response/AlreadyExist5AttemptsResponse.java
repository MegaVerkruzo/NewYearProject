package com.commercial.backend.security.response;

import com.commercial.backend.security.ApiException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.security.ApiException.alreadyExist5Attempts;

@Getter
public class AlreadyExist5AttemptsResponse {
    @Schema(example = "alreadyExist5Attempts")
    private final ApiException exception = alreadyExist5Attempts;
}
