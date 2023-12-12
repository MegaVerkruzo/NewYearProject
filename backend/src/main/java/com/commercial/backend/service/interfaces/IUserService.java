package com.commercial.backend.service.interfaces;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.state.State;

public interface IUserService {
    State addNewUserAndGetTokenWithHistory(User user);

    State checkTokenWithException(String authorization);

    Feedback addFeedback(User user, String feedback);

    User getUserByToken(String token);
}
