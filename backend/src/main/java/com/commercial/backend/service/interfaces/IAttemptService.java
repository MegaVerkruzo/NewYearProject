package com.commercial.backend.service.interfaces;

import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.game.GameStateKlass;

import java.time.OffsetDateTime;

public interface IAttemptService {
    GameStateKlass getAllInfo(User user);

    GameStateKlass addNewWord(User user, Answer answer, String word, OffsetDateTime currentMillis);
}
