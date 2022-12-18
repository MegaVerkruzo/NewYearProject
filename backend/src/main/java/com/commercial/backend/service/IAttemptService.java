package com.commercial.backend.service;

import java.util.Map;

public interface IAttemptService {
    Map<String, Object> getAllInfo(String token); // don't know parameters

    Map<String, Object> addNewWord(String token, String word);

}