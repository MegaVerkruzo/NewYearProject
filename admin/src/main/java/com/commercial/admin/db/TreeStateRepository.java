package com.commercial.admin.db;

import com.commercial.admin.db.entities.TreeState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TreeStateRepository extends CrudRepository<TreeState, Integer> {
    Optional<TreeState> findTreeStateByActiveGiftsCount(int id);
}
