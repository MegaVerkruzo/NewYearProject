package com.commercial.backend.service.interfaces;

import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.game.GameState;

public interface IUserService {
    GameState addNewUserAndGetTokenWithHistory(User user);

    GameState checkTokenWithException(String authorization);

    Feedback addFeedback(User user, String feedback);

    User getUserByToken(String token);
}
