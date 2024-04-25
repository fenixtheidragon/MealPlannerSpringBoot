package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController extends GeneralController<IngredientDto> {
  private final IngredientService service;
  public IngredientController(IngredientService service) {
    super(service);
    this.service = service;
  }
  @GetMapping("/names/{name}")
  public ResponseEntity<IngredientDto> findByName(@PathVariable String name) {
    return service.findByName(name);
  }
}
