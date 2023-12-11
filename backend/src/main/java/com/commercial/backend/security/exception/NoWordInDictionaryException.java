package com.commercial.backend.security.exception;

public class NoWordInDictionaryException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Expected: word in dictionary, but actual: no word in dictionary";
    }
}
