package com.commercial.backend.service;

import com.commercial.backend.db.ConfigRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@PropertySource("classpath:config.properties")
@AllArgsConstructor
public class ConfigService {
    private final Environment env;
    private final ConfigRepository configRepository;

    public OffsetDateTime getStartDate() {
        return configRepository.findById(env.getProperty("date.start.game")).get().getDateProperty();
    }

    public Long getDelta() {
        return configRepository.findById(env.getProperty("answers.delta.minutes")).get().getLongProperty();
    }

    public Long getTasksCount() {
        return configRepository.findById(env.getProperty("tasks.count")).get().getLongProperty();
    }

    public Boolean isFinishLottery() {
        return configRepository.findById(env.getProperty("is.lottery.finish")).get().getBooleanProperty();
    }

    public OffsetDateTime getLotteryDate() {
        return configRepository.findById(env.getProperty("lottery.date")).get().getDateProperty();
    }
}
