package com.commercial.admin.db;

import com.commercial.admin.db.entities.Feedback;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findFeedbackByUserId(Long userId);

    @Modifying
    @Query(value = "UPDATE feedback SET response = :response WHERE id = :id", nativeQuery = true)
    @Transactional
    void updateFeedbackById(String response, Long id);
}
