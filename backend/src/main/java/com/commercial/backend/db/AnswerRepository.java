package com.commercial.backend.db;

import com.commercial.backend.db.entities.Answer;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    @Override
    @NonNull
    List<Answer> findAll();
}
