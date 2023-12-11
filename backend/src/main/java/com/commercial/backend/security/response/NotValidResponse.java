package com.commercial.backend.security.response;

import com.commercial.backend.security.ApiException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import static com.commercial.backend.security.ApiException.notValid;

@Getter
public class NotValidResponse {
    @Schema(example = "notValid")
    private final ApiException exception = notValid;
}
