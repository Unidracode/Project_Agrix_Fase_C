package com.betrybe.agrix.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * comment.
 */

public record PersonDto(Long id, String username, Role role) {

}