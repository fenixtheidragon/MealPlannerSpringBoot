package com.example.mealplanner.services;

import com.example.mealplanner.dto.IngredientForRecipeDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishToIngredientRelationService extends GeneralService<DishToIngredientRelation> {
  ResponseEntity<RecipeDto> getRecipeDtoByDishId(Long dishId);

  ResponseEntity<List<DishToIngredientRelation>> saveIngredientToDishRelations(
      List<IngredientForRecipeDto> ingredientList, Long dishId
  );

  ResponseEntity<List<Ingredient>> updateIngredientsToDishRelations(List<Ingredient> ingredientList, Long dishId);

  ResponseEntity<HttpStatus> deleteIngredientsToDishRelations(List<Long> ingredientIdList, Long dishId);
}
