package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.services.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dishes")
public class DishController extends MealPlannerController<Dish> {
  private final DishService service;
  public DishController(DishService service) {
    super(service);
    this.service = service;
  }
  @GetMapping("/names/{name}")
  public ResponseEntity<Dish> findByName(@PathVariable String name) {
    return service.findByName(name);
  }
}
