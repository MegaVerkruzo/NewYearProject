package com.commercial.backend.service;

import com.commercial.backend.model.json.JsonUser;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class CommonLibrary {
    public static Long parseId(String token) throws NotRegisteredException, NotValidException {
        if (token == null) {
            throw new NotValidException();
        }
        // :TODO add exception handling
        String userJson = URLDecoder.decode(token, StandardCharsets.UTF_8).split("&")[1].split("=")[1];
        ObjectMapper mapper = new ObjectMapper();
        Long result;
        try {
            result = mapper.readValue(userJson, JsonUser.class).getId();
        } catch (JsonProcessingException e) {
            throw new NotValidException();
        }
        return result;
    }

    public static String getWordInUTF8(String word) {
        return new String(word.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}
