package com.commercial.backend.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "answers", schema = "public")
@JsonIgnoreProperties
public class Answer {
    private static Long size = 0L;

    public Answer() {
    }

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "date")
    private OffsetDateTime date;

    @Column(name = "description")
    private String description;

    public Answer(String word, Timestamp date, String description) {
        size++;
        this.id = size;
        this.word = word;
        this.date = date.toLocalDateTime().atOffset(OffsetDateTime.now().getOffset());
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", offsetDateTime=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
