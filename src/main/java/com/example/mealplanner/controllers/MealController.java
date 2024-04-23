package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Mealtime;
import com.example.mealplanner.services.MealtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/meals")
public class MealController extends MealPlannerController<Mealtime> {

  private final MealtimeService service;

  public MealController(MealtimeService service) {
    super(service);
    this.service = service;
  }

  @GetMapping("/names/{name}")
  public ResponseEntity<Mealtime> findByName(@PathVariable String name) {
    return service.findByName(name);
  }
}
