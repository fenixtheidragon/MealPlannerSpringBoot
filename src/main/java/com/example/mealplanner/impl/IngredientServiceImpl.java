package com.example.mealplanner.impl;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.helpers.validators.IngredientDtoRequestValidator;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.repositories.IngredientRepo;
import com.example.mealplanner.services.IngredientService;
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
public class IngredientServiceImpl implements IngredientService {
  private final IngredientRepo repository;
  private final IngredientDtoRequestValidator validator;
  private final static String resourceClassName = Ingredient.class.getName();

  @Override
  public ResponseEntity<List<IngredientDto>> findAll() {
    var ingredientDtoList = new ArrayList<IngredientDto>();
    repository.findAll().forEach(ingredient -> {
      var ingredientDto = new IngredientDto(ingredient);
      ingredientDtoList.add(ingredientDto);
    });
    return ResponseEntity.ok(ingredientDtoList);
  }

  @Override
  public ResponseEntity<IngredientDto> save(IngredientDto ingredientDto) {
    validator.validateSaveRequest(ingredientDto);
    ingredientDto = saveAndRefresh(ingredientDto);
    return ResponseEntity.ok(ingredientDto);
  }

  @Override
  public ResponseEntity<IngredientDto> update(IngredientDto ingredientDto) {
    validator.validateUpdateRequest(ingredientDto);
    ingredientDto = saveAndRefresh(ingredientDto);
    return ResponseEntity.ok(ingredientDto);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    validator.validateDeleteRequest(id);
    repository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<IngredientDto> findByName(String name) {
    var ingredient = repository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "name", name));
    return new ResponseEntity<>(new IngredientDto(ingredient), HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<IngredientDto> findById(Long id) {
    var ingredient = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    return new ResponseEntity<>(new IngredientDto(ingredient), HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<List<IngredientDto>> findByLetters(String letters) {
    return new ResponseEntity<>(getIngredientDtoList(letters), HttpStatus.OK);
  }

  private List<IngredientDto> getIngredientDtoList(String letters) {
    var ingredientList = repository.findByLetters(letters);
    var ingredientDtoList = new ArrayList<IngredientDto>();
    ingredientList.forEach(ingredient ->
        ingredientDtoList.add(new IngredientDto(ingredient))
    );
    return ingredientDtoList;
  }
  private IngredientDto saveAndRefresh(IngredientDto ingredientDto) {
    var ingredient = new Ingredient(ingredientDto);
    repository.save(ingredient);
    return new IngredientDto(ingredient);
  }
}
