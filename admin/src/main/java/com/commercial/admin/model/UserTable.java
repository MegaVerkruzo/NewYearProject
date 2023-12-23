package com.commercial.admin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class UserTable {
    private final Long id;
    private final String phone;
    private final String name;
    private final String surname;
    private String middleName;
    private String email;
    private String place;
    private String division;
    private int activeGifts;
    private int ticketNumber;
}
