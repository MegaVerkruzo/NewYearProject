package com.commercial.backend.db;

import com.commercial.backend.db.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findFeedbackByUserId(Long userId);
}
