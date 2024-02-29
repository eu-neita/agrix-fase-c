package com.betrybe.agrix.exeption;

import java.util.logging.Handler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Advice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {
  /**
   * Method.
   */

  @ExceptionHandler(FertilizerException.class)
  public ResponseEntity<String> handlerNotFoundException(FertilizerException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
}