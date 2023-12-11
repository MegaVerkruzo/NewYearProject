package com.commercial.backend.db;

import com.commercial.backend.db.entities.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findAllByUserIdOrderByDate(Long userId);
}
