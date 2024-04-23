package com.example.mealplanner.helpers.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<MealPlannerException> catchResourceNotFoundException(ResourceNotFoundException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        new MealPlannerException(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler
  public ResponseEntity<MealPlannerException> catchResourceAlreadyExistsException(
      ResourceAlreadyExistsException e
  ) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        new MealPlannerException(HttpStatus.CONFLICT.value(), e.getMessage()), HttpStatus.CONFLICT
    );
  }

  @ExceptionHandler
  public ResponseEntity<MealPlannerException> catchIllegalArgumentException(IllegalArgumentException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        new MealPlannerException(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST
    );
  }
}
