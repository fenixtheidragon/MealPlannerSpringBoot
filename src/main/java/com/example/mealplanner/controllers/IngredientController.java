package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController extends MealPlannerController<Ingredient> {
  private final IngredientService service;
  public IngredientController(IngredientService service) {
    super(service);
    this.service = service;
  }
  @GetMapping("/names/{name}")
  public ResponseEntity<Ingredient> findByName(@PathVariable String name) {
    return service.findByName(name);
  }
}
