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
        return configRepository.findByNameId(env.getProperty("date.start.game")).getDateProperty();
    }

    public Long getDelta() {
        return configRepository.findByNameId(env.getProperty("answers.delta.minutes")).getLongProperty();
    }

    public Long getTasksCount() {
        return configRepository.findByNameId(env.getProperty("tasks.count")).getLongProperty();
    }
}
