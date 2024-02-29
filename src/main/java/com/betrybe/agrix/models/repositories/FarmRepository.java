package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Farm entities.
 * This interface provides methods for performing CRUD operations on Farm entities.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
