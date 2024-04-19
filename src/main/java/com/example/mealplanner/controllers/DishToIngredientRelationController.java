package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.IngredientForRecipeDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import com.example.mealplanner.services.DishToIngredientRelationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/dishes")
@AllArgsConstructor
public class DishToIngredientRelationController {

  private final DishToIngredientRelationService dToIRelService;

  @GetMapping("/{dishId}/recipe")
  public ResponseEntity<RecipeDto> findDishRecipe(@PathVariable Long dishId) {
    return dToIRelService.getRecipeDtoByDishId(dishId);
  }

  @PostMapping("/{dishId}/recipe")
  public ResponseEntity<List<DishToIngredientRelation>> saveDishRecipe(
      @RequestBody List<IngredientForRecipeDto> ingredientList, @PathVariable Long dishId
  ) {
      return dToIRelService.saveIngredientToDishRelations(ingredientList, dishId);
  }
}
