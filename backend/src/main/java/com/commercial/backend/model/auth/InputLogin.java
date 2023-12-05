package com.commercial.backend.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputLogin {
    @JsonProperty("phone") public String phone;
    @JsonProperty("password") public String password;
}
