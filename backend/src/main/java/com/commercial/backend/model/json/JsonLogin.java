package com.commercial.backend.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JsonLogin {
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("password")
    private String password;
}
