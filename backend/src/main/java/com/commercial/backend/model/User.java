package com.commercial.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "public")
@JsonIgnoreProperties
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public User() {}

    public User(String phone, String name, String surname, String middleName, String email, String place, String password) {
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.place = place;

//        this.password = new BCryptPasswordEncoder().encode(password);
        this.passwordHash = password;
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

    public void setPasswordHash(String password) {
//        this.password = new BCryptPasswordEncoder().encode(password);
        this.passwordHash = password;
    }

    public Long getId() {
        return id;
    }

}
