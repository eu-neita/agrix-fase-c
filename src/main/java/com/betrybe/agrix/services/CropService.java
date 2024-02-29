package com.betrybe.agrix.services;

import com.betrybe.agrix.exeption.FertilizerException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Service class for managing Service entities.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FarmRepository farmRepository;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor.
   */
  @Autowired
  public CropService(
      CropRepository cropRepository,
      FarmRepository farmRepository,
      FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.farmRepository = farmRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * createCrop method.
   */
  public Optional<Crop> createCrop(Long farmId, Crop crop) {
    return farmRepository.findById(farmId).map(farm -> {
      crop.setFarm(farm);
      return cropRepository.save(crop);
    });
  }

  /**
   * getCropsByFarmId method.
   */
  public Optional<List<Crop>> getCropsByFarmId(Long farmId) {
    Optional<List<Crop>> cropConsult = cropRepository.findAllByFarmId(farmId);
    List<Crop> crops = cropConsult.get();
    if (crops.isEmpty() && !farmRepository.existsById(farmId)) {
      return Optional.empty();
    }
    return cropConsult;
  }

  /**
   * getAllCrops method.
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * getCropById method.
   */
  public Optional<Crop> getCropById(@PathVariable Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getCropByHarvestDateBetween(LocalDate start, LocalDate end) {
    return cropRepository.findAllByHarvestDateBetween(start, end);
  }

  /**
   * Add new fertilizer.
   */
  public String addFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(fertilizerId);
    if (cropOptional.isEmpty()) {
      throw new FertilizerException("Plantação não encontrada!");
    }
    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerException("Fertilizante não encontrado!");
    }
    Crop crop = cropOptional.get();
    Fertilizer fertilizer = fertilizerOptional.get();
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Get list for fertilizer.
   */
  public List<Fertilizer> getFertilizers(Long cropId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new FertilizerException("Plantação não encontrada!");
    }
    return cropOptional.get().getFertilizers();
  }
}
