package com.example.mealplanner.impl;

import com.example.mealplanner.dto.*;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.helpers.validators.DishMealtimeDtoRequestValidator;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Mealtime;
import com.example.mealplanner.models.composite.DishMealtime;
import com.example.mealplanner.repositories.*;
import com.example.mealplanner.services.DishMealtimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service@AllArgsConstructor
public class DishMealtimeServiceImpl implements DishMealtimeService {
  private final static String resourceClassName = DishMealtime.class.getName();
  private final DishMealtimeRepo dishMealtimeRepository;
  private final DishRepo dishRepository;
  private final MealtimeRepo mealtimeRepository;
  private final DishMealtimeDtoRequestValidator validator;

  @Override
  public ResponseEntity<List<DishMealtimeDto>> findAll() {
    List<DishMealtimeDto> dishMealtimeDtoList = new ArrayList<>();
    dishMealtimeRepository.findAll().forEach(dishMealtime -> {
      var dishMealtimeDto = new DishMealtimeDto(dishMealtime);
      dishMealtimeDtoList.add(dishMealtimeDto);
    });
    return ResponseEntity.ok(dishMealtimeDtoList);
  }

  @Override
  public ResponseEntity<DishMealtimeDto> save(DishMealtimeDto dishMealtimeDto) {
    validator.validateSaveRequest(dishMealtimeDto);
    dishMealtimeDto = saveAndRefresh(dishMealtimeDto);
    return ResponseEntity.ok(dishMealtimeDto);
  }

  @Override
  public ResponseEntity<DishMealtimeDto> update(DishMealtimeDto dishMealtimeDto) {
    validator.validateUpdateRequest(dishMealtimeDto);
    dishMealtimeDto = saveAndRefresh(dishMealtimeDto);
    return ResponseEntity.ok(dishMealtimeDto);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    validator.validateDeleteRequest(id);
    dishMealtimeRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<DishMealtimeDto> findById(Long id) {
    var dishMealtime = dishMealtimeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    return new ResponseEntity<>(new DishMealtimeDto(dishMealtime), HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<List<DishDto>> findDishDtoListByMealtimeId(Long mealtimeId) {
    var dishDtoList = getDishDtoListByMealTimeId(mealtimeId);
    return new ResponseEntity<>(dishDtoList, HttpStatus.FOUND);
  }

  private List<DishDto> getDishDtoListByMealTimeId(Long mealtimeId) {
    var dishDtoList = new ArrayList<DishDto>();
    var dishMealtimeList = dishMealtimeRepository.findDishMealtimesByMealtimeId(mealtimeId);
    dishMealtimeList.forEach(dishMealtime -> {
      var dish = dishMealtime.getDish();
      dishDtoList.add(new DishDto(dish));
    });
    return dishDtoList;
  }

  private DishMealtimeDto saveAndRefresh(DishMealtimeDto dishMealtimeDto) {
    var dish = getDishFrom(dishMealtimeDto);
    var mealtime = getMealtimeFrom(dishMealtimeDto);
    var dishMealtime = new DishMealtime(dishMealtimeDto, dish, mealtime);
    dishMealtimeRepository.save(dishMealtime);
    return new DishMealtimeDto(dishMealtime);
  }

  private Dish getDishFrom(DishMealtimeDto dishMealtimeDto) {
    var id = dishMealtimeDto.getDishId();
    return dishRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Dish.class.getName(), "id", String.valueOf(id)));
  }
  private Mealtime getMealtimeFrom(DishMealtimeDto dishMealtimeDto) {
    var id = dishMealtimeDto.getMealtimeId();
    return mealtimeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Mealtime.class.getName(), "id", String.valueOf(id)));
  }
}
