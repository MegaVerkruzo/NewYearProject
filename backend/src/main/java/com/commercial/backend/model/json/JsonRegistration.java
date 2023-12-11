package com.commercial.backend.model.json;

import com.commercial.backend.db.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JsonRegistration {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("place")
    private String place;
    @JsonProperty("division")
    private String division;

    public User getUser() {
        return new User(
                phone,
                name,
                surname,
                middleName,
                email,
                place,
                division
        );
    }
}
