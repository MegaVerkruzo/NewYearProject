package com.commercial.backend.service.interfaces;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.state.State;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;

public interface IUserService {
    State registerNewUser(User user);

    State getState(String authorization) throws NotRegisteredException;

    Feedback addFeedback(User user, String feedback);

    User getUserByToken(String token) throws NotRegisteredException;

    // :TODO need to do this
    // logic -> if token is null -> throw new NotRegisteredException
    // logic -> if token can't parse Long -> throw new NotValidException
    static Long parseId(String token) throws NotRegisteredException, NotValidException {
        return 0L;
    }
}
