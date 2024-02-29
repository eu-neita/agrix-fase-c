package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;

/**
 * Classe FertilizerDto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition, null);
  }

  /**
   * Method.
   */
  public static List<FertilizerDto> toListDto(List<Fertilizer> fertilizers) {
    return fertilizers.stream().map(fertilizer -> new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    )).toList();
  }
}