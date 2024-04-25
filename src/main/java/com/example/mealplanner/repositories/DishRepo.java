package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepo extends JpaRepository<Dish,Long> {
  Optional<Dish> findByName(String name);
}
