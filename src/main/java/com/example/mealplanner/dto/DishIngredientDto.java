package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishIngredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DishIngredientDto {
  private Long id;
  private Long dishId;
  private Long ingredientId;
  private Integer ingredientAmount = 0;
  private AmountType amountType = AmountType.GRAMS;

  public DishIngredientDto(DishIngredient dishIngredient) {
    this.id = dishIngredient.getId();
    this.dishId = dishIngredient.getDish().getId();
    this.ingredientId = dishIngredient.getIngredient().getId();
    this.ingredientAmount = dishIngredient.getIngredientAmount();
    this.amountType = dishIngredient.getAmountType();
  }
}
