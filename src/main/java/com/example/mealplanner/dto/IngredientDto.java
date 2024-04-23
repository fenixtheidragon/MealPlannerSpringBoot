package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.basic.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDto {
  private Long id;
  private String name;
  private Integer amount;
  private AmountType amountType;

  public IngredientDto(Ingredient ingredient) {
    this.id = ingredient.getId();
    this.name = ingredient.getName();
    this.amount = ingredient.getAvailableAmount();
    this.amountType = ingredient.getAmountType();
  }
}
