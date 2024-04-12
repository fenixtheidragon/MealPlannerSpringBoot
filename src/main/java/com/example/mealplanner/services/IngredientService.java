package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IngredientService {

  ResponseEntity<List<Ingredient>> findAll();

  ResponseEntity<Ingredient> findById(Long id);

  ResponseEntity<Ingredient> findByName(String name);

  ResponseEntity<Ingredient> save(Ingredient ingredient);

  ResponseEntity<Ingredient> update(Ingredient ingredient);

  ResponseEntity<HttpStatus> deleteByName(String name);
}
