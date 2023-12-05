package com.commercial.backend.model.auth;

import com.commercial.backend.model.ApiException;

public record TokenException(String token, ApiException exception) {
}
