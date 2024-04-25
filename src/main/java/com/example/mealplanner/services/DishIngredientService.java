package com.example.mealplanner.services;

import com.example.mealplanner.dto.DishIngredientDto;
import com.example.mealplanner.dto.IngredientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishIngredientService extends GeneralService<DishIngredientDto> {
  ResponseEntity<List<IngredientDto>> findIngredientDtoListByDishId(Long dishId);
}
