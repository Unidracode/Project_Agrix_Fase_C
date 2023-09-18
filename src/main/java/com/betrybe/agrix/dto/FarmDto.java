package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * comment.
 */

public record FarmDto(Integer id, String name, Double size) {
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}