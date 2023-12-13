package com.commercial.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("username")
    private String username;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("place")
    private String place;
    @JsonProperty("division")
    private String division;
}

