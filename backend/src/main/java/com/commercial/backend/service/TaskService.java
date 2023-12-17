package com.commercial.backend.service;

import com.commercial.backend.db.TaskRepository;
import com.commercial.backend.db.entities.Task;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CommonService commonService;
    private final ConfigService configService;

    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public Optional<Task> findPreviousAnswer(OffsetDateTime offsetDateTime) {
        OffsetDateTime previousDate = offsetDateTime.minusMinutes(configService.getDelta());
        logger.info("previousDate: " + previousDate);

        for (Task task : taskRepository.findAll().stream().sorted((o1, o2) -> {
            if (o1.getId() < o2.getId()) {
                return -1;
            } else if (o1.getId().equals(o2.getId())) {
                return 0;
            } else {
                return 1;
            }
        }).toList()) {
            logger.info("taskTime: " + commonService.getTaskStartTime(task));
            if (commonService.getTaskStartTime(task).isAfter(previousDate)) {
                logger.info("found this task: " + task.getId());
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }
}
