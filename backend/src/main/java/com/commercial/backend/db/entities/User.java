package com.commercial.backend.db.entities;

import com.commercial.backend.model.json.JsonRegistration;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

import static com.commercial.backend.service.CommonService.parseId;
import static com.commercial.backend.service.CommonService.parseUsername;

@Entity
@Table(name = "users", schema = "public")
@JsonIgnoreProperties
@Getter
@Setter
@ToString
@NoArgsConstructor
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
    @Column(name = "active_gifts")
    private int activeGifts;
    @Column(name = "ticket_number")
    private int ticketNumber;

    private static int randomNumber = 100;

    public User(String token, JsonRegistration json) throws NotRegisteredException, NotValidException {
        this.id = parseId(token);
        this.username = parseUsername(token);
        this.phone = json.getPhone();
        this.name = json.getName();
        this.surname = json.getSurname();
        this.middleName = json.getMiddleName();
        this.email = json.getEmail();
        this.place = json.getPlace();
        this.division = json.getDivision();
        this.activeGifts = 0;
        this.ticketNumber = randomNumber++;
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
