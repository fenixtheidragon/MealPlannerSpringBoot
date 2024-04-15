package com.example.mealplanner.controllers;

import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.services.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dishes")
public class DishController extends AbstractMealPlannerController<Dish> {
  private final DishService service;
  public DishController(DishService service) {
    super(service);
    this.service = service;
  }
//
//  @GetMapping
//  public ResponseEntity<List<Dish>> findAll() {
//    return service.findAll();
//  }
//
//  @PostMapping
//  public ResponseEntity<Dish> save(@RequestBody Dish newDish) {
//    return service.save(newDish);
//  }
//
//  @PutMapping
//  public ResponseEntity<Dish> update(@RequestBody Dish newDish) {
//    return service.update(newDish);
//  }
//
//  @DeleteMapping
//  public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
//    return service.deleteById(id);
//  }
//
//  @GetMapping("/{id}")
//  public ResponseEntity<Dish> findById(@PathVariable Long id) {
//    return service.findById(id);
//  }

  @GetMapping("/names/{name}")
  public ResponseEntity<Dish> findByName(@PathVariable String name) {
    return service.findByName(name);
  }
}
