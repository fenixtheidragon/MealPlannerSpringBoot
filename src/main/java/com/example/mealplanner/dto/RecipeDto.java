package com.example.mealplanner.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
  private Long dishId;
  private String dishName;
  private List<IngredientForRecipeDto> ingredientForRecipeDtoList;


}
