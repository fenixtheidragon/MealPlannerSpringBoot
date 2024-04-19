package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import lombok.Data;

@Data
public class IngredientForRecipeDto {
  private Long ingredientId;
  private String ingredientName;
  private int amountOfIngredient;
  private AmountType amountType;
}
