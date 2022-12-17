package com.commercial.backend.model;

import com.commercial.backend.security.JWTUtil;
import com.commercial.backend.security.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attempts", schema = "public")
@JsonIgnoreProperties
public class Attempt {

    private static Long size = 0L;
    @Id
    private Long id;

    @Column(name="phone")
    private String phone;

    @Column(name="word")
    private String word;

    @Column(name="countAttempt")
    private Integer countAttempt;

    @Column(name="dayOfWeek")
    private String dayOfWeek;

    public Attempt() {
        size++;
        this.id = size;
    }

    public Attempt(String phone, String word, Integer countAttempt, String dayOfWeek) {
        size++;
        this.id = size;
        this.phone = phone;
        this.word = word;
        this.countAttempt = countAttempt;
        this.dayOfWeek = dayOfWeek;
    }
}
