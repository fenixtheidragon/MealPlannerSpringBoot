package com.example.mealplanner.helpers.exceptions;

public class IngredientNotFoundException extends RuntimeException {
private final static String mainMessageText = "Ingredient with %s %s not found.";
    public IngredientNotFoundException(String fieldName, String fieldValueAsString) {
    super(String.format(mainMessageText, fieldName, fieldValueAsString));
  }
}
