package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Mealtime;
import com.example.mealplanner.models.composite.DishMealtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishMealtimeRepo extends JpaRepository<DishMealtime, Long> {
  public List<DishMealtime> findDishMealtimesByMealtimeId(Long mealtimeId);
}
