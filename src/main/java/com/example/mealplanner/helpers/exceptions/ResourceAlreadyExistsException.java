package com.example.mealplanner.helpers.exceptions;

public class ResourceAlreadyExistsException extends ResourceException{

  public ResourceAlreadyExistsException(
      String resourceClassName, String fieldName, String fieldValueAsString
  ) {
    super(resourceClassName, fieldName, fieldValueAsString, "already exists");
  }
}
