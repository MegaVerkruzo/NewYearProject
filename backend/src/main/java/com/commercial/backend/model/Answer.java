package com.commercial.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answers", schema = "public")
public class Answer {
    private static Long size = 0L;

    @Id
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "day_of_month")
    private int dayOfMonth;

    @Column(name = "post_link")
    private String postLink;

    @Column(name = "description")
    private String description;

    public Answer() {
        size++;
        this.id = size;
    }

    public Answer(String word, int dayOfMonth, String postLink, String description) {
        size++;
        this.id = size;
        this.word = word;
        this.dayOfMonth = dayOfMonth;
        this.postLink = postLink;
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

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", dayOfMonth='" + dayOfMonth + '\'' +
                ", postLink='" + postLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
