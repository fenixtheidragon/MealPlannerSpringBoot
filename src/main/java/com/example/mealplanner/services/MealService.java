package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Meal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MealService {

  ResponseEntity<List<Meal>> findAll();

  ResponseEntity<Meal> save(Meal meal);

  ResponseEntity<Meal> update(Meal meal);

  ResponseEntity<HttpStatus> deleteById(Long id);

  ResponseEntity<Meal> findById(Long id);

  ResponseEntity<Meal> findByName(String name);
}
