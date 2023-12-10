package com.commercial.backend.db;

import com.commercial.backend.db.entities.ConfigField;
import org.springframework.data.repository.CrudRepository;

public interface ConfigRepository extends CrudRepository<ConfigField, String> {
    ConfigField findByNameId(String nameId);
}
