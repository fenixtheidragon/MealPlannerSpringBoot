package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.http.ResponseEntity;

public interface IngredientService extends GeneralService<Ingredient>{
  ResponseEntity<Ingredient> findByName(String name);
}
