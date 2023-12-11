package com.commercial.backend.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JsonWord {
    @JsonProperty("word")
    private String word;
}
