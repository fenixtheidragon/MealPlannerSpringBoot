package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.MealtimeDto;
import com.example.mealplanner.models.basic.Mealtime;
import com.example.mealplanner.services.MealtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/meals")
public class MealtimeController extends MealPlannerController<MealtimeDto> {

  private final MealtimeService service;

  public MealtimeController(MealtimeService service) {
    super(service);
    this.service = service;
  }
}
