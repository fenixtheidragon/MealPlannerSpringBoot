package com.example.mealplanner.controllers;

import com.example.mealplanner.helpers.enums.MealCategory;
import com.example.mealplanner.models.basic.Meal;
import com.example.mealplanner.services.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meals")
public class MealController extends AbstractMealPlannerController<Meal> {

  private final MealService service;
  public MealController(MealService service) {
    super(service);
    this.service = service;
  }

  @GetMapping("/names/{name}")
  public ResponseEntity<Meal> findByCategory(MealCategory category) {
    return service.findByName(category);
  }
}
