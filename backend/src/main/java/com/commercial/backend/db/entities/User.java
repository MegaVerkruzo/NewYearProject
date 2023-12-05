package com.commercial.backend.db.entities;

import com.commercial.backend.security.JWTUtil;
import com.commercial.backend.security.PasswordEncoder;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "place")
    private String place;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "token")
    private String token;

    @Column(name = "feedback")
    private String feedback;

    public User() {
        size++;
        this.id = size;
    }

    // :TODO Make static method
    public void loginUser(String phone) {
        size++;
        this.id = size;
        this.phone = phone;
        this.token = JWTUtil.generateToken(this);
    }

    public User(String phone, String name, String surname, String middleName, String email, String place, String password, Boolean isThisPasswordHash) {
        size++;
        this.id = size;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.place = place;
        this.passwordHash = isThisPasswordHash ? password : PasswordEncoder.getHash(password);
        this.token = JWTUtil.generateToken(this);
    }
}
