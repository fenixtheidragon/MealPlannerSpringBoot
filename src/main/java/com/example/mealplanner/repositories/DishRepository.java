package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
  Optional<Dish> findByName(String name);

  List<Ingredient> findIngredientsByDishId(Long dishId);
}
