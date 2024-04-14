package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishService {

  ResponseEntity<List<Dish>> findAll();

  ResponseEntity<Dish> save(Dish dish);

  ResponseEntity<Dish> update(Dish dish);

  ResponseEntity<HttpStatus> deleteById(Long id);

  ResponseEntity<Dish> findById(Long id);

  ResponseEntity<Dish> findByName(String name);
}
