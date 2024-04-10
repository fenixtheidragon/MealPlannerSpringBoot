package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
