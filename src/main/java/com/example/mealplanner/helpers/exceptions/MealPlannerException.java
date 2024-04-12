package com.example.mealplanner.helpers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealPlannerException {
  private int statusCode;
  private String message;
}
