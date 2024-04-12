package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class IngredientController {

  private final IngredientService service;

  @GetMapping("/ingredients")
  public ResponseEntity<List<Ingredient>> findAll() {
    return service.findAll();
  }

  @PostMapping("/ingredients")
  public ResponseEntity<Ingredient> save(@RequestBody Ingredient newIngredient) {
    return service.save(newIngredient);
  }

  @GetMapping("ingredients/{id}")
  public ResponseEntity<Ingredient> findById(@RequestParam Long id) {
    return service.findById(id);
  }
}
