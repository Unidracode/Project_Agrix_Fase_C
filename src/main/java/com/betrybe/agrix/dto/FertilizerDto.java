package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * comment.
 */

public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * comment.
   */

  public static FertilizerDto fromFertilizer(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  /**
   * comment.
   */

  public Fertilizer toFertilizer() {
    Fertilizer fertilizer = new Fertilizer();

    fertilizer.setName(this.name);
    fertilizer.setBrand(this.brand);
    fertilizer.setComposition(this.composition);
    return fertilizer;
  }
}