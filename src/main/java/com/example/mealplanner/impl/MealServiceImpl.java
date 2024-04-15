package com.example.mealplanner.impl;

import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Meal;
import com.example.mealplanner.repositories.MealRepository;
import com.example.mealplanner.services.MealService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class MealServiceImpl implements MealService {
  private final MealRepository repository;
  private final static String resourceClassName = Meal.class.getName();

  @Override
  public ResponseEntity<List<Meal>> findAll() {
    return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Meal> save(Meal meal) {
    var optionalMeal = repository.findByName(meal.getName());
    if (optionalMeal.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", meal.getName());
    }
    return new ResponseEntity<>(repository.save(meal), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Meal> update(Meal newMeal) {
    validateUpdateRequest(newMeal);
    return new ResponseEntity<>(repository.save(newMeal), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Meal.class.getName(), "id", String.valueOf(id)));
    repository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Meal> findById(Long id) {
    var meal = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Meal.class.getName(), "id", String.valueOf(id)));
    return new ResponseEntity<>(meal, HttpStatus.FOUND);
  }
  @Override
  public ResponseEntity<Meal> findByName(String name) {
    var meal = repository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException(Meal.class.getName(), "name", name));
    return new ResponseEntity<>(meal, HttpStatus.FOUND);
  }

  private void validateUpdateRequest(Meal newMeal) {
    repository.findById(newMeal.getId())
        .orElseThrow(() ->
            new ResourceNotFoundException(resourceClassName, "id", String.valueOf(newMeal.getId()))
        );
    var optionalMeal = repository.findByName(newMeal.getName());
    if (optionalMeal.isPresent() && !optionalMeal.get().getId().equals(newMeal.getId())) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", newMeal.getName());
    }
  }
}
