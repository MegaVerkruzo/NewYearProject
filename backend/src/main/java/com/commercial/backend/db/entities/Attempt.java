package com.commercial.backend.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "attempts", schema = "public")
@JsonIgnoreProperties
@Data
@NoArgsConstructor
public class Attempt {
    @Getter
    @Setter
    private static Long size = 0L;

    @Id
    private Long id;
    @Column(name="id_user") private Long userId;
    private String word;
    private OffsetDateTime date;

    public Attempt(Long userId, String word, Timestamp date) {
        size++;
        this.id = size;
        this.userId = userId;
        this.word = word;
        this.date = date.toLocalDateTime().atOffset(OffsetDateTime.now().getOffset());
    }

    public Attempt(Long userId, String word, OffsetDateTime date) {
        size++;
        this.id = size;
        this.userId = userId;
        this.word = word;
        this.date = date;
    }
}
