package com.example.mealplanner.services;

import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GeneralService<T> {
  ResponseEntity<List<T>> findAll();

  ResponseEntity<T> save(T entity);

  ResponseEntity<T> update(T entity);

  ResponseEntity<HttpStatus> deleteById(Long id);

  ResponseEntity<T> findById(Long id);
}
