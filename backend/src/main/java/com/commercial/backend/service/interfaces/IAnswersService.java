package com.commercial.backend.service.interfaces;


import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.Task;

import java.time.OffsetDateTime;
import java.util.List;

public interface IAnswersService {
    Task findPreviousAnswer(OffsetDateTime offsetDateTime);

    int countCorrectAnswers(List<Attempt> attempts);

    OffsetDateTime getMaxDate();
}