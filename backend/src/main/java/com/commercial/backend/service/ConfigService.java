package com.commercial.backend.service;

import com.commercial.backend.db.ConfigRepository;
import com.commercial.backend.security.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class ConfigService {
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

    public String getLotteryLinkMessage() {
        return configRepository
                .findById("lottery_link_text")
                .orElseThrow(BadRequestException::new)
                .getStringProperty();
    }

    public String getLotteryMessage() {
        return configRepository
                .findById("lottery_message_text")
                .orElseThrow(BadRequestException::new)
                .getStringProperty();
    }

    public String getBeforeGameMessage() {
        return configRepository
                .findById("hello_text")
                .orElseThrow(BadRequestException::new)
                .getStringProperty();
    }

    public String getAfterLotteryMessage() {
        return configRepository
                .findById("after_lottery")
                .orElseThrow(BadRequestException::new)
                .getStringProperty();
    }

    public String getWaitNextGameMessage() {
        return configRepository
                .findById("wait_next_game")
                .orElseThrow(BadRequestException::new)
                .getStringProperty();
    }
}
