package com.commercial.backend.db;

import com.commercial.backend.db.entities.TreeState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TreeStateRepository extends CrudRepository<TreeState, Integer> {
    Optional<TreeState> findTreeStateByActiveGiftsCount(int id);
}
