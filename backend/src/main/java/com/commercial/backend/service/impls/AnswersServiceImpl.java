package com.commercial.backend.service.impls;

import com.commercial.backend.db.AnswerRepository;
import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.Task;
import com.commercial.backend.service.interfaces.IAnswersService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswersServiceImpl implements IAnswersService {

    private final AnswerRepository answerRepository;
    private final DeltaService deltaService;

    private final Logger logger = LoggerFactory.getLogger(AnswersServiceImpl.class);

    @Override
    public Task findPreviousAnswer(OffsetDateTime offsetDateTime) {
        OffsetDateTime previousDate = deltaService.getDeltaDown(offsetDateTime);
        logger.info("previousDate: " + previousDate);

        for (Task task : answerRepository.findAll()) {
            if (task.getDate().isAfter(previousDate)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public int countCorrectAnswers(List<Attempt> attempts) {
        int correctAnswers = 0;
        for (Attempt attempt : attempts) {
            for (Task task : answerRepository.findAll()) {
                if (task.getWord().equals(attempt.getWord())
                        && task.getDate().isBefore(attempt.getDate())
                        && attempt.getDate().isBefore(deltaService.getDeltaUp(task.getDate()))) {
                    correctAnswers++;
                }
            }
        }
        return correctAnswers;
    }

    @Override
    public OffsetDateTime getMaxDate() {
        OffsetDateTime maxDate = OffsetDateTime.MIN;
        for (Task task : answerRepository.findAll()) {
            if (task.getDate().isAfter(maxDate)) {
                maxDate = task.getDate();
            }
        }
        return maxDate;
    }
}
