package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Dish;
import org.springframework.http.ResponseEntity;

public interface DishService extends GeneralService<Dish>{
    ResponseEntity<Dish> findByName(String name);
}
