package com.commercial.backend.service;

import com.commercial.backend.db.ConfigRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class DeltaService {
    private final ConfigRepository configRepository;

    public OffsetDateTime getDeltaUp(OffsetDateTime offsetDateTime) {
        return offsetDateTime.plusMinutes(configRepository.findByNameId("answers_delta_minutes").getLongProperty());
    }

    public OffsetDateTime getDeltaDown(OffsetDateTime offsetDateTime) {
        return offsetDateTime.minusMinutes(configRepository.findByNameId("answers_delta_minutes").getLongProperty());
    }
}
