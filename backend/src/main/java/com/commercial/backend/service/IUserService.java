package com.commercial.backend.service;

import com.commercial.backend.model.TokenException;
import com.commercial.backend.model.User;
import org.springframework.data.util.Pair;

import java.util.Map;

public interface IUserService {
    TokenException addNewUserAndGetTokenWithHistory(User user);

    TokenException getTokenWithCheckingPassword(User user, String rawPassword);

    Pair<String, String> checkTokenWithException(String token);

    Map<String, Object> addFeedback(User user, String feedback);

    User getUserByToken(String token);
}
