package com.example.mealplanner.helpers.exceptions;

public class ResourceNotFoundException extends RuntimeException {
private final static String mainMessageText = "%s with %s %s not found.";
    public ResourceNotFoundException(String fieldClassName, String fieldName, String fieldValueAsString) {
    super(String.format(mainMessageText, fieldClassName, fieldName, fieldValueAsString));
  }
}
