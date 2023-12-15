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
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final DeltaService deltaService;
    private final CommonService commonService;

    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public Optional<Task> findPreviousAnswer(OffsetDateTime offsetDateTime) {
        OffsetDateTime previousDate = deltaService.getDeltaDown(offsetDateTime);
        logger.info("previousDate: " + previousDate);

        for (Task task : taskRepository.findAll()) {
            if (commonService.getTaskStartTime(task).isAfter(previousDate)) {
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }
}
