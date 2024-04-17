package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
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

  @GetMapping("/{dishId}/ingredients")
  public ResponseEntity<List<Ingredient>> findAllIngredientsOfDish(@PathVariable Long dishId) {
    return dToIRelService.findIngredientsByDishId(dishId);
  }

  @PostMapping("/{dishId}/ingredient")
  public ResponseEntity<DishToIngredientRelation> saveDishToIngredientRelation(
      @RequestBody DishToIngredientRelation relation, @PathVariable Long dishId
  ) {
    return dToIRelService.save(relation);
  }

  @PostMapping("/{dishId}/ingredients")
  public ResponseEntity<List<DishToIngredientRelation>> saveDishToIngredientRelations(
      @RequestBody List<DishToIngredientRelation> relationList, @PathVariable Long dishId
  ) {
      return dToIRelService.saveIngredientsToDishRelations(relationList, dishId);
  }
}
