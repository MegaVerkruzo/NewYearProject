package com.commercial.backend.service;

import com.commercial.backend.model.Answer;
import com.commercial.backend.model.User;

import java.time.OffsetDateTime;
import java.util.Map;

public interface IAttemptService {
    Map<String, Object> getAllInfo(User user);

    Map<String, Object> addNewWord(User user, Answer answer, String word, OffsetDateTime currentMillis);
}
