package com.example.mealplanner.services;

import com.example.mealplanner.dto.DishIngredientDto;
import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.models.composite.DishIngredient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishIngredientService {
  ResponseEntity<RecipeDto> getRecipeDtoByDishId(Long dishId);

  ResponseEntity<List<DishIngredient>> saveIngredientToDishRelations(
      List<IngredientDto> ingredientList, Long dishId
  );

  ResponseEntity<List<Ingredient>> updateIngredientsToDishRelations(List<Ingredient> ingredientList, Long dishId);

  ResponseEntity<HttpStatus> deleteIngredientsToDishRelations(List<Long> ingredientIdList, Long dishId);
}
