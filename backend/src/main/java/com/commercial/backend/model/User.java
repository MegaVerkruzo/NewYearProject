package com.commercial.backend.model;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String phone;

    private String name;
    private String surname;

    @Column(name="middlename")
    private String middleName;
    private String email;
    private String place;

    @Column(name="passwordhash")
    private String passwordHash;

    public User() {}

    public User(String phone, String surname, String middleName, String email, String place, String passwordHash) {
        this.phone = phone;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.place = place;
        this.passwordHash = passwordHash;
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

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
