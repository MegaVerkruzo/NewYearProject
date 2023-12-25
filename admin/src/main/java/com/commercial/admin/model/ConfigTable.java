package com.commercial.admin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@ToString
@Getter
@AllArgsConstructor
public class ConfigTable {
    private final String id;
    private final String strProperty;
    private final LocalDateTime dateTimeProperty;
    private final Long longProperty;
    private final Boolean booleanProperty;
}
