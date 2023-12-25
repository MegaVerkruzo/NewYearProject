package com.commercial.admin.db;

import com.commercial.admin.db.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    @Modifying
    @Query(value = "UPDATE users SET active_gifts = :active_gifts WHERE id = :id", nativeQuery = true)
    @Transactional
    void updateUsersById(Long active_gifts, Long id);

    @Transactional
    void deleteById(Long id);
}
