package com.example.mealplanner.impl;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.helpers.validators.DishDtoRequestValidator;
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
  private final DishDtoRequestValidator validator;

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
    validator.validateSaveRequest(dishDto);
    var dish = new Dish(dishDto);
    repository.save(dish);
    dishDto = new DishDto(dish);
    return ResponseEntity.ok(dishDto);
  }

  @Override
  public ResponseEntity<DishDto> update(DishDto dishDto) {
    validator.validateUpdateRequest(dishDto);
    var dish = new Dish(dishDto);
    repository.save(dish);
    dishDto = new DishDto(dish);
    return ResponseEntity.ok(dishDto);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    validator.validateDeleteRequest(id);
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
  public ResponseEntity<DishDto> findById(Long id) {
    var dish = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    return new ResponseEntity<>(new DishDto(dish), HttpStatus.FOUND);
  }
}
