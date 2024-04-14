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

  @PutMapping("/ingredients")
  public ResponseEntity<Ingredient> update(@RequestBody Ingredient newIngredient) {
    return service.update(newIngredient);
  }

  @DeleteMapping("/ingredients")
  public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
    return service.deleteById(id);
  }

  @GetMapping("ingredients/{id}")
  public ResponseEntity<Ingredient> findById(@PathVariable Long id) {
    return service.findById(id);
  }

//  @GetMapping("/ingredients/{name}")
//  public ResponseEntity<Ingredient> findByName(@PathVariable String name) {
//    return service.findByName(name);
//  }
}
