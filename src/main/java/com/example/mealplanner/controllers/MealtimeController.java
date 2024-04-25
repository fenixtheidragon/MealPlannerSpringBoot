package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.dto.DishIngredientDto;
import com.example.mealplanner.dto.MealtimeDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.composite.DishMealtime;
import com.example.mealplanner.services.DishIngredientService;
import com.example.mealplanner.services.DishMealtimeService;
import com.example.mealplanner.services.DishService;
import com.example.mealplanner.services.MealtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/mealtimes")
public class MealtimeController extends GeneralController<MealtimeDto> {
  private final MealtimeService mealtimeService;
  private final DishMealtimeService dishMealtimeService;

  public MealtimeController(MealtimeService mealtimeService, DishMealtimeService dishMealtimeService) {
    super(mealtimeService);
    this.mealtimeService = mealtimeService;
    this.dishMealtimeService = dishMealtimeService;
  }
}
