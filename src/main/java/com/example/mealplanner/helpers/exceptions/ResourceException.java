package com.example.mealplanner.helpers.exceptions;

public abstract class ResourceException extends RuntimeException {
  private final static String mainMessageText = "%s with %s = %s %s.";

  public ResourceException(
      String resourceClassName, String fieldName, String fieldValueAsString, String problem
  ) {
    super(String.format(mainMessageText, resourceClassName, fieldName, fieldValueAsString, problem));
  }
}
