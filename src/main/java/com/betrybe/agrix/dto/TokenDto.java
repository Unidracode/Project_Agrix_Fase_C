package com.betrybe.agrix.dto;

/**
 * comment.
 */

public record TokenDto(String token) {

  /**
   * comment.
   */

  public static TokenDto fromEntity(String token) {
    return new TokenDto(token);
  }
}