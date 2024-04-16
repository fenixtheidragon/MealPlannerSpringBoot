package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Meal;
import com.example.mealplanner.services.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/meals")
public class MealController extends AbstractMealPlannerController<Meal> {

  private final MealService service;

  public MealController(MealService service) {
    super(service);
    this.service = service;
  }

  @GetMapping("/names/{name}")
  public ResponseEntity<Meal> findByName(@PathVariable String name) {
    return service.findByName(name);
  }
}
