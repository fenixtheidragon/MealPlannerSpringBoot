package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Meal;
import org.springframework.http.ResponseEntity;

public interface MealService extends GeneralService<Meal>{
  ResponseEntity<Meal> findByName(String name);
}
