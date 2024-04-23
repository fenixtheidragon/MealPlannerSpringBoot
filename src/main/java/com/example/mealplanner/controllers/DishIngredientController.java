package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.models.composite.DishIngredient;
import com.example.mealplanner.services.DishIngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/dishes")
@AllArgsConstructor
public class DishIngredientController {

  private final DishIngredientService dToIRelService;

  @GetMapping("/{dishId}/recipe")
  public ResponseEntity<RecipeDto> findDishRecipe(@PathVariable Long dishId) {
    return dToIRelService.getRecipeDtoByDishId(dishId);
  }

  @PostMapping("/{dishId}/recipe")
  public ResponseEntity<List<DishIngredient>> saveDishRecipe(
      @RequestBody List<IngredientDto> ingredientList, @PathVariable Long dishId
  ) {
      return dToIRelService.saveIngredientToDishRelations(ingredientList, dishId);
  }
}
