package com.commercial.backend.security.response;

import com.commercial.backend.security.ApiException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.security.ApiException.oldState;

@Getter
public class OldStateResponse {
    @Schema(example = "oldState")
    private final ApiException exception = oldState;
}
