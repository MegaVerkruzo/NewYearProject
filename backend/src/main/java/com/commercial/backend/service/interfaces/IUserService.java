package com.commercial.backend.service.interfaces;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.state.State;
import com.commercial.backend.security.exception.NotRegisteredException;

public interface IUserService {
    State registerNewUser(User user);

    State getState(String authorization) throws NotRegisteredException;

    Feedback addFeedback(User user, String feedback);

    User getUserByToken(String token) throws NotRegisteredException;
}
