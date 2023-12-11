package com.commercial.backend.service.impls;

import com.commercial.backend.db.AnswerRepository;
import com.commercial.backend.db.entities.Answer;
import com.commercial.backend.db.entities.Attempt;
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

    private final Logger logger = LoggerFactory.getLogger(AttemptService.class);

    @Override
    public Answer findPreviousAnswer(OffsetDateTime offsetDateTime) {
        OffsetDateTime previousDate = deltaService.getDeltaDown(offsetDateTime);
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
                        && attempt.getDate().isBefore(deltaService.getDeltaUp(answer.getDate()))) {
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
