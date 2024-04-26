package com.example.mealplanner.dto;

import com.example.mealplanner.models.basic.Dish;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RecipeDto {
  private Long dishId;
  private String dishName;
  private List<IngredientDto> ingredientDtoList;

  public RecipeDto(Dish dish) {
    this.dishId = dish.getId();
    this.dishName = dish.getName();
    this.ingredientDtoList = extractIngredientDtoListFrom(dish);
  }

  private static List<IngredientDto> extractIngredientDtoListFrom(Dish dish) {
    var ingredientDtoList = new ArrayList<IngredientDto>();
    dish.getDishIngredientSet().forEach(dishIngredient -> {
      var ingredientDto = new IngredientDto(dishIngredient.getIngredient());
      ingredientDtoList.add(ingredientDto);
    });
    return  ingredientDtoList;
  }
}
