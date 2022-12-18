package com.commercial.backend.model;

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

    @Column(name="count_attempt")
    private Integer countAttempt;

    @Column(name="day_of_month")
    private Integer dayOfMonth;

    public Attempt() {
        size++;
        this.id = size;
    }

    public Attempt(String phone, String word, Integer countAttempt, Integer dayOfMonth) {
        size++;
        this.id = size;
        this.phone = phone;
        this.word = word;
        this.countAttempt = countAttempt;
        this.dayOfMonth = dayOfMonth;
    }

    public Attempt(int id, String phone, String word, Integer countAttempt, Integer dayOfMonth) {
        size++;
        this.id = size;
        this.phone = phone;
        this.word = word;
        this.countAttempt = countAttempt;
        this.dayOfMonth = dayOfMonth;
    }

    public static Long getSize() {
        return size;
    }

    public static void setSize(Long size) {
        Attempt.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCountAttempt() {
        return countAttempt;
    }

    public void setCountAttempt(Integer countAttempt) {
        this.countAttempt = countAttempt;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", word='" + word + '\'' +
                ", countAttempt=" + countAttempt +
                ", dayOfMonth='" + dayOfMonth + '\'' +
                '}';
    }
}
