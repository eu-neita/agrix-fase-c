package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * Data Transfer Object (DTO) representing farm data.
 * This record class provides a convenient way to transfer farm data.
 */
public record FarmDto(Long id, String name, double size) {
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
