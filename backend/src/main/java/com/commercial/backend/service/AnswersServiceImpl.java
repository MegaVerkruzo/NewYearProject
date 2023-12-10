package com.commercial.backend.service;

import com.commercial.backend.db.AnswerRepository;
import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.Attempt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

import static com.commercial.backend.Common.getDeltaDown;
import static com.commercial.backend.Common.getDeltaUp;


@Service
public class AnswersServiceImpl implements AnswersService {

    private final AnswerRepository answerRepository;
    private final Logger logger = LoggerFactory.getLogger(AttemptService.class);

    public AnswersServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer findPreviousAnswer(OffsetDateTime offsetDateTime) {
        OffsetDateTime previousDate = getDeltaDown(offsetDateTime);
        logger.info("previousDate: " + previousDate);

        for (Answer answer : answerRepository.findAll()) {
            if (answer.getDate().isAfter(previousDate)) {
                return answer;
            }
        }
        return null;
    }

    @Override
    public int countCorrectAnswers(List<Attempt> attempts) {
        int correctAnswers = 0;
        for (Attempt attempt : attempts) {
            for (Answer answer : answerRepository.findAll()) {
                if (answer.getWord().equals(attempt.getWord())
                        && answer.getDate().isBefore(attempt.getDate())
                        && attempt.getDate().isBefore(getDeltaUp(answer.getDate()))) {
                    correctAnswers++;
                }
            }
        }
        return correctAnswers;
    }

    @Override
    public OffsetDateTime getMaxDate() {
        OffsetDateTime maxDate = OffsetDateTime.MIN;
        for (Answer answer : answerRepository.findAll()) {
            if (answer.getDate().isAfter(maxDate)) {
                maxDate = answer.getDate();
            }
        }
        return maxDate;
    }
}
