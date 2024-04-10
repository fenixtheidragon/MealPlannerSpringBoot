package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
  Optional<Ingredient> findByName(String name);

  Optional<Ingredient> findById(Long id);

  List<Ingredient> findAll();

  Ingredient save(Ingredient ingredient);

  Ingredient update(Ingredient ingredient);

  void deleteByName(String name);
}
