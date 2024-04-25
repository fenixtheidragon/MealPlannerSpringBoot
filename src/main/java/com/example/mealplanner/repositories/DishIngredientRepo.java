package com.example.mealplanner.repositories;

import com.example.mealplanner.models.composite.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishIngredientRepo extends JpaRepository<DishIngredient,Long> {
  List<DishIngredient> findDishIngredientsByDishId(Long dishId);
}
