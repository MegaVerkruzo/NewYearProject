package com.commercial.backend.service.interfaces;

import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.game.GameState;

import java.time.OffsetDateTime;

public interface IAttemptService {
    GameState getAllInfo(User user);

    GameState addNewWord(User user, Answer answer, String word, OffsetDateTime currentMillis);
}
