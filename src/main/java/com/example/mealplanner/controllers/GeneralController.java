package com.example.mealplanner.controllers;

import com.example.mealplanner.services.GeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GeneralController<T> {

  private final GeneralService<T> service;

  public GeneralController(GeneralService<T> service) {
    this.service = service;
  }
  @GetMapping
  public ResponseEntity<List<T>> findAll() {
    return service.findAll();
  }

  @PostMapping
  public ResponseEntity<T> save(@RequestBody T newEntity) {
    return service.save(newEntity);
  }

  @PutMapping
  public ResponseEntity<T> update(@RequestBody T newEntity) {
    return service.update(newEntity);
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
    return service.deleteById(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<T> findById(@PathVariable Long id) {
    return service.findById(id);
  }
}
