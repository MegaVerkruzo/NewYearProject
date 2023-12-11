package com.commercial.backend.db;

import com.commercial.backend.db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPhone(String phone);

    @Modifying
    @Query(value = "UPDATE users SET feedback = :feedback WHERE id = :id", nativeQuery = true)
    void updateFeedbackByPhone(String feedback, Long id);
}
