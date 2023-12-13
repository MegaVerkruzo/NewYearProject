package com.commercial.backend.db;

import com.commercial.backend.db.entities.Task;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    @NonNull
    List<Task> findAll();
}
