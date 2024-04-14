package com.example.mealplanner.helpers.exceptions;

public class ResourceNotFoundException extends ResourceException {

  public ResourceNotFoundException(String fieldClassName, String fieldName, String fieldValueAsString) {
    super(fieldClassName, fieldName, fieldValueAsString, "not found");
  }
}
