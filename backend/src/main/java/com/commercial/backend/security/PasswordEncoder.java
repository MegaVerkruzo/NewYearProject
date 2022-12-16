package com.commercial.backend.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    private PasswordEncoder() {}

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String getHash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean checkHash(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
