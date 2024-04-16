package com.example.mealplanner.impl;

import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Day;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.repositories.DayRepository;
import com.example.mealplanner.repositories.DishRepository;
import com.example.mealplanner.services.DayService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class DayServiceImpl implements DayService {
  private final static String resourceClassName = Day.class.getName();
  private final DayRepository repository;

  @Override
  public ResponseEntity<List<Day>> findAll() {
    return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Day> save(Day day) {
    var optionalDay = repository.findByName(day.getName());
    if (optionalDay.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", day.getName().toString());
    }
    return new ResponseEntity<>(repository.save(day), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Day> update(Day day) {
    validateUpdateRequest(day);
    return new ResponseEntity<>(repository.save(day), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    repository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Day> findById(Long id) {
    var day = repository.findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    return new ResponseEntity<>(day, HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<Day> findByName(DayOfWeek name) {
    var day = repository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "name", name.toString()));
    return new ResponseEntity<>(day, HttpStatus.FOUND);
  }

  private void validateUpdateRequest(Day day) {
    repository.findById(day.getId())
        .orElseThrow(() ->
            new ResourceNotFoundException(resourceClassName, "id", String.valueOf(day.getId()))
        );
    var optionalDay = repository.findByName(day.getName());
    if (optionalDay.isPresent() && !optionalDay.get().getId().equals(day.getId())) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", day.getName().toString());
    }

  }
}
