package com.commercial.backend.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonUser {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
}
