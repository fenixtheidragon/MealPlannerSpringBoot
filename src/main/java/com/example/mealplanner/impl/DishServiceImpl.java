package com.example.mealplanner.impl;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.repositories.DishRepo;
import com.example.mealplanner.services.DishService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class DishServiceImpl implements DishService {
  private final static String resourceClassName = Dish.class.getName();
  private final DishRepo repository;

  @Override
  public ResponseEntity<List<DishDto>> findAll() {
    List<DishDto> dishDtoList = new ArrayList<>();
    repository.findAll().forEach(dish -> {
      var dishDto = new DishDto(dish);
      dishDtoList.add(dishDto);
    });
    return ResponseEntity.ok(dishDtoList);
  }

  @Override
  public ResponseEntity<DishDto> save(DishDto dishDto) {
    var optionalDish = repository.findByName(dishDto.getName());
    if (optionalDish.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", dishDto.getName());
    }
    var dish = new Dish(dishDto);
    repository.save(dish);
    return new ResponseEntity<>(, HttpStatus.OK);
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
  public ResponseEntity<DishDto> findByName(String name) {
    var dish = repository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "name", name));
    return new ResponseEntity<>(new DishDto(dish), HttpStatus.FOUND);
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
