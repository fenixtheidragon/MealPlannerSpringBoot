package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.basic.Dish;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DishDto {
  private Long id;
  private String name;
  private String recipeDescription;
  private Integer availableAmount;
  private AmountType amountType;

  public DishDto(Dish dish) {
    this.id = dish.getId();
    this.name = dish.getName();
    this.recipeDescription = dish.getRecipeDescription();
    this.availableAmount = dish.getAvailableAmount();
    this.amountType = dish.getAmountType();
  }
}
