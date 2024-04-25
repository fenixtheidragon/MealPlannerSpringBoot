package com.example.mealplanner.services;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.dto.DishMealtimeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishMealtimeService extends GeneralService<DishMealtimeDto> {
  ResponseEntity<List<DishDto>> findDishDtoListByMealtimeId(Long mealtimeId);
}
