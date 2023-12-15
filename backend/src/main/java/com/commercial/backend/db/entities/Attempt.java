package com.commercial.backend.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "attempt", schema = "public")
@JsonIgnoreProperties
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Attempt {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "attempt_generator")
    @SequenceGenerator(name = "attempt_generator", sequenceName = "attempt_seq", allocationSize = 1)
    private Long id;
    @Column(name="user_id") private Long userId;
    private String word;
    private OffsetDateTime date;

    public Attempt(Long userId, String word, OffsetDateTime date) {
        this.userId = userId;
        this.word = word;
        this.date = date;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Attempt attempt = (Attempt) o;
        return getId() != null && Objects.equals(getId(), attempt.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
