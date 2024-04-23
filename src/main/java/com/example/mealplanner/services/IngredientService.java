package com.example.mealplanner.services;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.http.ResponseEntity;

public interface IngredientService extends GeneralService<IngredientDto>{
  ResponseEntity<Ingredient> findByName(String name);
}
