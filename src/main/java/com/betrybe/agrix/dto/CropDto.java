package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * comment.
 */

public record CropDto(
    Long id, Long farmId, String name, Double plantedArea, LocalDate plantedDate,
    LocalDate harvestDate) {

  /**
   * comment.
   */

  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getFarmId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }

  /**
   * comment.
   */

  public Crop toEntity() {
    Crop crop = new Crop();
    crop.setFarmId(this.farmId);
    crop.setId(this.id);
    crop.setName(this.name);
    crop.setPlantedArea(this.plantedArea);
    crop.setPlantedDate(this.plantedDate);
    crop.setHarvestDate(this.harvestDate);
    return crop;
  }
}