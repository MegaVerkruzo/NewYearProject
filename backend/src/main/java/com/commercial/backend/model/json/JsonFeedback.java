package com.commercial.backend.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JsonFeedback {
    @JsonProperty("feedback")
    private String feedback;
}
