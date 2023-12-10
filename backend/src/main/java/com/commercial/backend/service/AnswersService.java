package com.commercial.backend.service;


import com.commercial.backend.model.Answer;
import com.commercial.backend.model.Attempt;

import java.time.OffsetDateTime;
import java.util.List;

public interface AnswersService {
    Answer findPreviousAnswer(OffsetDateTime offsetDateTime);

    int countCorrectAnswers(List<Attempt> attempts);

    OffsetDateTime getMaxDate();
}