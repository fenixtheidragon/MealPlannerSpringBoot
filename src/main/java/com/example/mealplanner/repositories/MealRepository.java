package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal,Long> {
  Optional<Meal> findByName(String name);
}
