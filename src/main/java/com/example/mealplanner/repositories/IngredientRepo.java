package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepo extends GeneralRepo<Ingredient> {
  Optional<Ingredient> findByName(String name);
}
