package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Ingredient;

import java.util.List;

public interface IngredientService {
  Ingredient save(Ingredient ingredient);

  List<Ingredient> findAll();

  Ingredient findById(Long id);

  Ingredient findByName(String name);

  Ingredient update(Ingredient ingredient);

  void deleteByName(String name);
}
