package com.commercial.backend.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputRegistration {
    @JsonProperty("name") public String name;
    @JsonProperty("surname") public String surname;
    @JsonProperty("middleName") public String middleName;
    @JsonProperty("email") public String email;
    @JsonProperty("phone") public String phone;
    @JsonProperty("password") public String password;
    @JsonProperty("place") public String place;
}
