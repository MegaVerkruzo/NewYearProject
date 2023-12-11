package com.commercial.backend.security.response;

import com.commercial.backend.security.ApiException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.security.ApiException.alreadyExistCorrectAttempt;

@Getter
public class AlreadyExistCorrectAttemptResponse {
    @Schema(example = "alreadyExistCorrectAttempt")
    private final ApiException exception = alreadyExistCorrectAttempt;
}
