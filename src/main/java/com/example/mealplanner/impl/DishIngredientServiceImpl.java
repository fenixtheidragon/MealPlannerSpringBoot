package com.example.mealplanner.impl;

import com.example.mealplanner.dto.DishIngredientDto;
import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.helpers.validators.DishIngredientDtoRequestValidator;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.models.composite.DishIngredient;
import com.example.mealplanner.repositories.DishIngredientRepo;
import com.example.mealplanner.repositories.DishRepo;
import com.example.mealplanner.repositories.IngredientRepo;
import com.example.mealplanner.services.DishIngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DishIngredientServiceImpl implements DishIngredientService {
  private final static String resourceClassName = DishIngredient.class.getName();
  private final DishIngredientRepo dishIngredientRepository;
  private final DishRepo dishRepository;
  private final IngredientRepo ingredientRepository;
  private final DishIngredientDtoRequestValidator validator;

  @Override
  public ResponseEntity<List<DishIngredientDto>> findAll() {
    List<DishIngredientDto> dishIngredientDtoList = new ArrayList<>();
    dishIngredientRepository.findAll().forEach(dishIngredient -> {
      var dishIngredientDto = new DishIngredientDto(dishIngredient);
      dishIngredientDtoList.add(dishIngredientDto);
    });
    return ResponseEntity.ok(dishIngredientDtoList);
  }

  @Override
  public ResponseEntity<DishIngredientDto> save(DishIngredientDto dishIngredientDto) {
    validator.validateSaveRequest(dishIngredientDto);
    var dish = getDishFrom(dishIngredientDto);
    var ingredient = getIngredientFrom(dishIngredientDto);
    dishIngredientRepository.save(new DishIngredient(dishIngredientDto, dish, ingredient));
    return ResponseEntity.ok(dishIngredientDto);
  }

  @Override
  public ResponseEntity<DishIngredientDto> update(DishIngredientDto dishIngredientDto) {
    validator.validateUpdateRequest(dishIngredientDto);
    var dish = getDishFrom(dishIngredientDto);
    var ingredient = getIngredientFrom(dishIngredientDto);
    dishIngredientRepository.save(new DishIngredient(dishIngredientDto, dish, ingredient));
    return ResponseEntity.ok(dishIngredientDto);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    validator.validateDeleteRequest(id);
    dishIngredientRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<DishIngredientDto> findById(Long id) {
    var dishIngredient = dishIngredientRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    return new ResponseEntity<>(new DishIngredientDto(dishIngredient), HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<List<IngredientDto>> findIngredientDtoListByDishId(Long dishId) {
    var ingredientDtoList = new ArrayList<IngredientDto>();
    var dishIngredientList = dishIngredientRepository.findDishIngredientsByDishId(dishId);
    dishIngredientList.forEach(dishIngredient -> {
      var ingredient = dishIngredient.getIngredient();
      ingredientDtoList.add(new IngredientDto(ingredient));
    });
    return new ResponseEntity<>(ingredientDtoList, HttpStatus.FOUND);
  }

  private Dish getDishFrom(DishIngredientDto dishIngredientDto) {
    var id = dishIngredientDto.getDishId();
    return dishRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Dish.class.getName(), "id", String.valueOf(id)));
  }
  private Ingredient getIngredientFrom(DishIngredientDto dishIngredientDto) {
    var id = dishIngredientDto.getIngredientId();
    return ingredientRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Ingredient.class.getName(), "id", String.valueOf(id)));
  }
}
