package com.commercial.backend.service;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.auth.TokenException;
import com.commercial.backend.model.feedback.Feedback;

public interface IUserService {
    TokenException addNewUserAndGetTokenWithHistory(User user);

    TokenException getTokenWithCheckingPassword(User user, String rawPassword);

    TokenException checkTokenWithException(String token);

    Feedback addFeedback(User user, String feedback);

    User getUserByToken(String token);
}
