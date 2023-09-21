package com.betrybe.agrix.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.models.entities.Person;

/**
 * comment.
 */

public record NewPersonDto(Long id, String username, String password, Role role) {

  public Person toEntity() {
    return new Person(id, username, password, role);
  }
}