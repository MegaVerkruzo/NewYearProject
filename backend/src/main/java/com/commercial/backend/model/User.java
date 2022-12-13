package com.commercial.backend.model;

import com.commercial.backend.security.JWTUtil;
import com.commercial.backend.security.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "public")
@JsonIgnoreProperties
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="phone")
    private String phone;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="email")
    private String email;

    @Column(name="place")
    private String place;

    @Column(name="password_hash")
    private String passwordHash;

    @Column(name="token")
    private String token;

    public User() {}

    public void loginUser(String phone) {
        this.phone = phone;
        this.token = JWTUtil.generateToken(this);
    }

    public User(String phone, String name, String surname, String middleName, String email, String place, String password, Boolean isThisPasswordHash) {
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.place = place;
        this.passwordHash = isThisPasswordHash ? password : PasswordEncoder.getHash(password);
        this.token = JWTUtil.generateToken(this);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String rawPassword) {
        this.passwordHash = PasswordEncoder.getHash(rawPassword);
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", place='" + place + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
