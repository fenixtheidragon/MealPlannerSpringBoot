package com.example.mealplanner.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainPageController {
  @RequestMapping("/")
  public String welcome() {
    return "Welcome to Meal Planner!";
  }
}
