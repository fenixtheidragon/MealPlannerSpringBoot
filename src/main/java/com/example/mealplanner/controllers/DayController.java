package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Day;
import com.example.mealplanner.services.DayService;
import com.example.mealplanner.services.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/days")
public class DayController extends AbstractMealPlannerController<Day> {

  private final DayService service;

  public DayController(DayService service) {
    super(service);
    this.service = service;
  }

  @GetMapping("/names/{name}")
  public ResponseEntity<Day> findByName(@PathVariable DayOfWeek name) {
    return service.findByName(name);
  }
}
