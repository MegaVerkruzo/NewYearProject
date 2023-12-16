package com.commercial.backend.service;

import com.commercial.backend.db.ConfigRepository;
import com.commercial.backend.security.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class ConfigService {
    private final Logger logger = LoggerFactory.getLogger(ConfigService.class);

    private final ConfigRepository configRepository;

    public OffsetDateTime getStartDate() {
        return configRepository
                .findById("date_start_game")
                .orElseThrow(BadRequestException::new)
                .getDateTime();
    }

    public Long getDelta() {
        return configRepository
                .findById("answers_delta_minutes")
                .orElseThrow(BadRequestException::new)
                .getLongProperty();
    }

    public Long getTasksCount() {
        return configRepository
                .findById("tasks_count")
                .orElseThrow(BadRequestException::new)
                .getLongProperty();
    }

    public Boolean isFinishLottery() {
        return configRepository
                .findById("is_lottery_finish")
                .orElseThrow(BadRequestException::new)
                .getBooleanProperty();
    }

    public OffsetDateTime getLotteryDate() {
        return configRepository
                .findById("lottery_date")
                .orElseThrow(BadRequestException::new)
                .getDateTime();
    }
}
