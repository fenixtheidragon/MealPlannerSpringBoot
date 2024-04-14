package com.example.mealplanner.impl;

import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.repositories.DishRepository;
import com.example.mealplanner.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class DishServiceImpl implements DishService {
  private final static String resourceClassName = Dish.class.getName();
  private final DishRepository repository;

  @Override
  public ResponseEntity<List<Dish>> findAll() {
    var dishList = repository.findAll();
    return new ResponseEntity<>(dishList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Dish> save(Dish dish) {
    var optionalDish = repository.findByName(dish.getName());
    if (optionalDish.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", dish.getName());
    }
    return new ResponseEntity<>(repository.save(dish), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Dish> update(Dish dish) {
    validateUpdateRequest(dish);
    return new ResponseEntity<>(repository.save(dish), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    repository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Dish> findByName(String name) {
    var dish = repository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "name", name));
    return new ResponseEntity<>(dish, HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<Dish> findById(Long id) {
    var dish = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    return new ResponseEntity<>(dish, HttpStatus.FOUND);
  }

  private void validateUpdateRequest(Dish dish) {
    repository.findById(dish.getId())
        .orElseThrow(() ->
            new ResourceNotFoundException(resourceClassName, "id", String.valueOf(dish.getId()))
        );
    var optionalDish = repository.findByName(dish.getName());
    if (optionalDish.isPresent() && !optionalDish.get().getId().equals(dish.getId())) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", dish.getName());
    }
  }
}
