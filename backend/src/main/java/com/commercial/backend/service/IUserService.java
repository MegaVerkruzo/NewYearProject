package com.commercial.backend.service;

import com.commercial.backend.model.Feedback;
import com.commercial.backend.model.TokenException;
import com.commercial.backend.model.User;

public interface IUserService {
    TokenException addNewUserAndGetTokenWithHistory(User user);

    TokenException getTokenWithCheckingPassword(User user, String rawPassword);

    TokenException checkTokenWithException(String token);

    Feedback addFeedback(User user, String feedback);

    User getUserByToken(String token);
}
