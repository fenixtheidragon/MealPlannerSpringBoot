package com.example.mealplanner.services;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.models.basic.Dish;
import org.springframework.http.ResponseEntity;

public interface DishService extends GeneralService<DishDto>{
    ResponseEntity<DishDto> findByName(String name);
}
