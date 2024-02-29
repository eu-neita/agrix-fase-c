package com.betrybe.agrix.controllers.dto;

/**
 * Data Transfer Object (DTO) representing a generic response.
 * This record class provides a convenient way to wrap data in a response object.
 */
public record ResponseDto<T>(String message, T data) {

}
