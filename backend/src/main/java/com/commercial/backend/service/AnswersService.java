package com.commercial.backend.service;

import com.commercial.backend.db.TaskRepository;
import com.commercial.backend.db.entities.Attempt;
import com.commercial.backend.db.entities.Task;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswersService {
    private final TaskRepository taskRepository;
    private final DeltaService deltaService;

    private final Logger logger = LoggerFactory.getLogger(AnswersService.class);

    public Task findPreviousAnswer(OffsetDateTime offsetDateTime) {
        OffsetDateTime previousDate = deltaService.getDeltaDown(offsetDateTime);
        logger.info("previousDate: " + previousDate);

        for (Task task : taskRepository.findAll()) {
            if (task.getDate().isAfter(previousDate)) {
                return task;
            }
        }
        return null;
    }

    public int countCorrectAnswers(List<Attempt> attempts) {
        int correctAnswers = 0;
        for (Attempt attempt : attempts) {
            for (Task task : taskRepository.findAll()) {
                if (task.getWord().equals(attempt.getWord())
                        && task.getDate().isBefore(attempt.getDate())
                        && attempt.getDate().isBefore(deltaService.getDeltaUp(task.getDate()))) {
                    correctAnswers++;
                }
            }
        }
        return correctAnswers;
    }

    public OffsetDateTime getMaxDate() {
        OffsetDateTime maxDate = OffsetDateTime.MIN;
        for (Task task : taskRepository.findAll()) {
            if (task.getDate().isAfter(maxDate)) {
                maxDate = task.getDate();
            }
        }
        return maxDate;
    }
}
