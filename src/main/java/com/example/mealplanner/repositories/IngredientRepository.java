package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
  Optional<Ingredient> findByName(String name);

  List<Dish> findDishesByIngredientId(Long ingredientId);
}
