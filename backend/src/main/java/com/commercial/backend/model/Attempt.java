package com.commercial.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "attempts", schema = "public")
@JsonIgnoreProperties
public class Attempt {

    private static Long size = 0L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "word")
    private String word;

    @Column(name = "date")
    private OffsetDateTime date;


    public Attempt(String phone, String word, Timestamp date) {
        size++;
        this.id = size;
        this.phone = phone;
        this.word = word;
        this.date = date.toLocalDateTime().atOffset(OffsetDateTime.now().getOffset());
    }

    public Attempt(String phone, String word, OffsetDateTime date) {
        size++;
        this.id = size;
        this.phone = phone;
        this.word = word;
        this.date = date;
    }

    public Attempt() {

    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
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

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
