package com.example.mealplanner.repositories;

import com.example.mealplanner.models.composite.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishIngredientRepo extends JpaRepository<DishIngredient,Long> {
}
