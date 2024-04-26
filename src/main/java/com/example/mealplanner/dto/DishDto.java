package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.basic.Dish;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DishDto {
  private Long id;
  private String name = "";
  private String recipeDescription = "";
  private Integer amount = 0;
  private AmountType amountType = AmountType.GRAMS;

  public DishDto(Dish dish) {
    this.id = dish.getId();
    this.name = dish.getName();
    this.recipeDescription = dish.getRecipeDescription();
    this.amount = dish.getAvailableAmount();
    this.amountType = dish.getAmountType();
  }
}
