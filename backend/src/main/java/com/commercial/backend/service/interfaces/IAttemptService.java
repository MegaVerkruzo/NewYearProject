package com.commercial.backend.service.interfaces;

import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.state.State;
import com.commercial.backend.security.exception.NotValidException;

import java.time.OffsetDateTime;

public interface IAttemptService {
    State getAllInfo(User user);

    State addNewWord(User user, Answer answer, String word, OffsetDateTime currentMillis) throws NotValidException;
}
