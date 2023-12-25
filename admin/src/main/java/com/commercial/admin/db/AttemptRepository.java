package com.commercial.admin.db;


import com.commercial.admin.db.entities.Attempt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findAllByUserIdOrderByDate(Long userId);

    @Transactional
    void deleteAllByUserId(Long userId);
}
