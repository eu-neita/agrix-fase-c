package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for managing Crop entities.
 * This interface provides methods for performing CRUD operations on Farm entities.
 */

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  Optional<List<Crop>> findAllByFarmId(Long farmId);

  List<Crop> findAllByHarvestDateBetween(LocalDate start, LocalDate end);
}
