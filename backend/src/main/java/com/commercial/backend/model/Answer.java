package com.commercial.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "answers", schema = "public")
@JsonIgnoreProperties
public class Answer {
    private static Long size = 0L;

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

    public static Long getSize() {
        return size;
    }

    public static void setSize(Long size) {
        Answer.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
