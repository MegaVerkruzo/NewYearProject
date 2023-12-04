package com.commercial.backend.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
        Attempt.size = size;
    }

    public String getPhone() {
        return phone;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public String getWord() {
        return word;
    }
}
