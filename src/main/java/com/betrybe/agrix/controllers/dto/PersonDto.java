package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.security.Role;

/**
 * PersonDto.
 */
public record PersonDto(Long id, String username, Role role) {

}
