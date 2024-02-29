package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.controllers.dto.ResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing Crop entities.
 */
@RestController
@RequestMapping
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Handles HTTP POST requests to create a new crop.
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> newCrop(@PathVariable Long farmId, @RequestBody Crop newCrop) {
    Optional<Crop> cropOptional = cropService.createCrop(farmId, newCrop);
    if (cropOptional.isEmpty()) {
      ResponseDto<Crop> responseDto = new ResponseDto<>(
          String.format("Fazenda não encontrada!", farmId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    CropDto cropDto = new CropDto(
        cropOptional.get().getId(),
        cropOptional.get().getName(),
        cropOptional.get().getPlantedArea(),
        farmId,
        cropOptional.get().getPlantedDate(),
        cropOptional.get().getHarvestDate());
    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
  }

  /**
   * Handles HTTP GET requests to all crops same farmId.
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<?> getCropsByFarmId(@PathVariable Long farmId) {
    Optional<List<Crop>> cropsOptional = cropService.getCropsByFarmId(farmId);
    if (cropsOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Fazenda não encontrada!");
    }

    List<CropDto> cropD = cropsOptional.get().stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            farmId,
            crop.getPlantedDate(),
            crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropD);
  }

  /**
   * Handles HTTP GET requests to all crops.
   */
  @GetMapping("/crops")
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();
    List<CropDto> cropDtos = crops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Handles HTTP GET requests to crops by id.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<?> getCropById(@PathVariable Long id) {
    Optional<Crop> crops = cropService.getCropById(id);
    if (crops.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Plantação não encontrada!");
    }
    CropDto cropDtos = new CropDto(
        crops.get().getId(),
        crops.get().getName(),
        crops.get().getPlantedArea(),
        crops.get().getFarm().getId(),
        crops.get().getPlantedDate(),
        crops.get().getHarvestDate());
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Handles HTTP GET requests to crops by planted date.
   */
  @GetMapping("/crops/search")
  public ResponseEntity<List<CropDto>> getByPlantedDate(LocalDate start, LocalDate end) {
    List<Crop> crops = cropService.getCropByHarvestDateBetween(start, end);

    List<CropDto> cropDtos = crops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Handles HTTP POST requests to add a new fertilizer to a crop.
   */
  @PostMapping("/crops/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    String response = cropService.addFertilizer(cropId, fertilizerId);
    return ResponseEntity.created(null).body(response);
  }

  /**
   * Handles HTTP GET list of Fertilizers.
   */
  @GetMapping("/crops/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizers(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getFertilizers(cropId);
    return ResponseEntity.status(HttpStatus.OK).body(FertilizerDto.toListDto(fertilizers));
  }
}