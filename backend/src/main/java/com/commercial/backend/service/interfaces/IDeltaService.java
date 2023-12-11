package com.commercial.backend.service.interfaces;

import java.time.OffsetDateTime;

public interface IDeltaService {
    OffsetDateTime getDeltaUp(OffsetDateTime offsetDateTime);

    OffsetDateTime getDeltaDown(OffsetDateTime offsetDateTime);
}
