package com.commercial.backend.service.interfaces;


import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.Attempt;

import java.time.OffsetDateTime;
import java.util.List;

public interface IAnswersService {
    Answer findPreviousAnswer(OffsetDateTime offsetDateTime);

    int countCorrectAnswers(List<Attempt> attempts);

    OffsetDateTime getMaxDate();
}