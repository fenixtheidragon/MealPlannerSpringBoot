package com.example.mealplanner.services;

import com.example.mealplanner.dto.IngredientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IngredientService extends GeneralService<IngredientDto>{
  ResponseEntity<IngredientDto> findByName(String name);

  ResponseEntity<List<IngredientDto>> findByLetters(String letters);

}
