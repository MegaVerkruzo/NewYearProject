package com.commercial.backend.db;

import com.commercial.backend.db.entities.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    @Override
    Iterable<Answer> findAll();
}
