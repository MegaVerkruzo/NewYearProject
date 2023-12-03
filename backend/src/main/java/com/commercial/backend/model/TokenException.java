package com.commercial.backend.model;

public record TokenException(String token, ApiException exception) {
}
