package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Day;
import org.springframework.http.ResponseEntity;

import java.time.DayOfWeek;

public interface DayService extends GeneralService<Day> {
  ResponseEntity<Day> findByName(DayOfWeek name);
}
