package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.basic.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDto {
  private Long ingredientId;
  private String ingredientName;
  private int amountOfIngredient;
  private AmountType amountType;

  public IngredientDto(Ingredient ingredient) {
    this.ingredientId = ingredient.getId();
    this.ingredientName = ingredient.getName();
    this.amountOfIngredient = ingredient.getAvailableAmount();
    this.amountType = ingredient.getAmountType();
  }
}
