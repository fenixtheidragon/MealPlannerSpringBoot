package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ingredients")
@AllArgsConstructor
public class IngredientController {

  private final IngredientService service;

  @GetMapping
  public ResponseEntity<List<Ingredient>> findAll() {
    return service.findAll();
  }

  @PostMapping
  public ResponseEntity<Ingredient> save(@RequestBody Ingredient newIngredient) {
    return service.save(newIngredient);
  }

  @PutMapping
  public ResponseEntity<Ingredient> update(@RequestBody Ingredient newIngredient) {
    return service.update(newIngredient);
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
    return service.deleteById(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ingredient> findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @GetMapping("/names/{name}")
  public ResponseEntity<Ingredient> findByName(@PathVariable String name) {
    return service.findByName(name);
  }
}
