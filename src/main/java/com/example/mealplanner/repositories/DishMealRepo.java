package com.example.mealplanner.repositories;

import com.example.mealplanner.models.composite.DishMealtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishMealRepo extends JpaRepository<DishMealtime, Long> {
}