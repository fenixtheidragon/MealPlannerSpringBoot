package com.example.mealplanner.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@AllArgsConstructor
public class MainPageController {
  @RequestMapping("/")
  public ResponseEntity<String> welcome() {
    return new ResponseEntity<>("Welcome to Meal Planner!", HttpStatus.OK);
  }
}
