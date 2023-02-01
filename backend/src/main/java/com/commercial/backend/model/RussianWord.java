package com.commercial.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "russian_words", schema = "public")
@JsonIgnoreProperties
public class RussianWord {
    private static Long size = 0L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "word")
    private String word;

    public RussianWord() {
        size++;
        this.id = size;
    }

    public RussianWord(Long id, String word) {
        size++;
        this.id = id;
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
