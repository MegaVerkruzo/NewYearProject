package com.commercial.backend.db.entities;

import com.commercial.backend.security.JWTUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "public")
@JsonIgnoreProperties
@Data
// :TODO Think about get&setters, because lombok maybe is decision
public class User {
    @Getter
    @Setter
    private static Long size = 0L;

    @Id
    private Long id;
    private String phone;
    private String username;
    private String name;
    private String surname;
    @Column(name = "middle_name") private String middleName;
    private String email;
    private String place;
    private String division;
    private String token;

    public User() {
        size++;
        this.id = size;
    }

    public User(
            String phone,
            String name,
            String surname,
            String middleName,
            String email,
            String place,
            String division
    ) {
        size++;
        this.id = size;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.place = place;
        this.token = JWTUtil.generateToken(this);
        this.division = division;
    }
}
