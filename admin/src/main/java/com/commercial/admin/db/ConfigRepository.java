package com.commercial.admin.db;

import com.commercial.admin.db.entities.ConfigField;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigRepository extends CrudRepository<ConfigField, String> {
    @NonNull Optional<ConfigField> findById(@NonNull String id);

    List<ConfigField> findAllBy();
}
