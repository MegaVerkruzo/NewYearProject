package com.commercial.backend.service;

import com.commercial.backend.db.AnswersRepository;
import com.commercial.backend.model.Answer;
import com.commercial.backend.model.Attempt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

import static com.commercial.backend.Common.getDeltaDown;
import static com.commercial.backend.Common.getDeltaUp;


@Service
public class AnswersServiceImpl implements AnswersService {

    private AnswersRepository answersRepository;
    private final Logger logger = LoggerFactory.getLogger(AttemptService.class);

    public AnswersServiceImpl(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

//    :APPROVED
    @Override
    public Answer findPreviousAnswer(OffsetDateTime offsetDateTime) {
        OffsetDateTime previousDate = getDeltaDown(offsetDateTime);
        logger.info("previousDate: " + previousDate);

        for (Answer answer : answersRepository.findAll()) {
            if (answer.getDate().isAfter(previousDate)) {
                return answer;
            }
        }
        return null;
    }

//     :APPROVED
    @Override
    public int countCorrectAnswers(List<Attempt> attempts) {
        int correctAnswers = 0;
        for (Attempt attempt : attempts) {
            for (Answer answer : answersRepository.findAll()) {
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
        for (Answer answer : answersRepository.findAll()) {
            if (answer.getDate().isAfter(maxDate)) {
                maxDate = answer.getDate();
            }
        }
        return maxDate;
    }
}