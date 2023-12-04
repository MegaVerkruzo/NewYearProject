package com.commercial.backend.db.entities;

import com.commercial.backend.security.JWTUtil;
import com.commercial.backend.security.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", schema = "public")
@JsonIgnoreProperties
// :TODO Think about get&setters, because lombok maybe is decision
public class User {

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        User.size = size;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public String getPlace() {
        return place;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getToken() {
        return token;
    }

    public String getFeedback() {
        return feedback;
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
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
