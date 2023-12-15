package com.commercial.backend.db;

import com.commercial.backend.db.entities.ConfigField;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfigRepository extends CrudRepository<ConfigField, String> {
    Optional<ConfigField> findById(String id);
}
