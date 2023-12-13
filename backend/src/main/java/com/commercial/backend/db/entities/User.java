package com.commercial.backend.db.entities;

import com.commercial.backend.model.json.JsonRegistration;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.commercial.backend.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "users", schema = "public")
@JsonIgnoreProperties
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    private Long id;
    private String phone;
    private String username;
    private String name;
    private String surname;
    @Column(name = "middle_name")
    private String middleName;
    private String email;
    private String place;
    private String division;

    public User(
            Long id,
            String phone,
            String name,
            String surname,
            String middleName,
            String email,
            String place,
            String division
    ) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.place = place;
        this.division = division;
    }

    //
    public User(String token, JsonRegistration json) throws NotRegisteredException, NotValidException {
        this.id = UserService.parseId(token);
        this.phone = json.getPhone();
        this.name = json.getName();
        this.surname = json.getSurname();
        this.middleName = json.getMiddleName();
        this.email = json.getEmail();
        this.place = json.getPlace();
        this.division = json.getDivision();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
