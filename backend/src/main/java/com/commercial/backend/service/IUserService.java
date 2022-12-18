package com.commercial.backend.service;

import com.commercial.backend.model.User;
import org.springframework.data.util.Pair;

import java.util.Map;

public interface IUserService {
    Pair<String, String> addNewUserAndGetTokenWithHistory(User user);

    Pair<String, String> getTokenWithCheckingPassword(User user, String rawPassword);

    Pair<String, String> checkTokenWithException(String token);

    Pair<String, String> addFeedback(String token, String feedback);
}
