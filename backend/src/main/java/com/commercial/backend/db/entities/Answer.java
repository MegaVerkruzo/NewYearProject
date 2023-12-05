package com.commercial.backend.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "answers", schema = "public")
@JsonIgnoreProperties
@Data
public class Answer {
    @Getter
    @Setter
    private static Long size = 0L;

    public Answer() {
    }

    @Id
    @GeneratedValue
    private Long id;

    private String word;

    private OffsetDateTime date;

    private String description;

    public Answer(String word, Timestamp date, String description) {
        size++;
        this.id = size;
        this.word = word;
        this.date = date.toLocalDateTime().atOffset(OffsetDateTime.now().getOffset());
        this.description = description;
    }
}
