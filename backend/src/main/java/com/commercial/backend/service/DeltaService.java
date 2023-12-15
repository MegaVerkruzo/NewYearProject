package com.commercial.backend.service;

import com.commercial.backend.db.ConfigRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class DeltaService {
    private final ConfigService configService;

    public OffsetDateTime getDeltaUp(OffsetDateTime offsetDateTime) {
        return offsetDateTime.plusMinutes(configService.getDelta());
    }

    public OffsetDateTime getDeltaDown(OffsetDateTime offsetDateTime) {
        return offsetDateTime.minusMinutes(configService.getDelta());
    }
}
