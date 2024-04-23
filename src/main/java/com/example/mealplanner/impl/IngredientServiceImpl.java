package com.example.mealplanner.impl;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
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
  private final static String resourceClassName = Ingredient.class.getName();
  private final IngredientRepo repository;

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
    SaveIngredientDtoRequestValidator
    var optionalIngredient = repository.findByName(ingredientDto.getName());
    if (optionalIngredient.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", ingredientDto.getName());
    }
    var ingredient = new Ingredient(ingredientDto);
    repository.save(ingredient);
    return ResponseEntity.ok(ingredientDto);
  }

  @Override
  public ResponseEntity<IngredientDto> update(IngredientDto newIngredientDto) {

    return new ResponseEntity<>(repository.save(newIngredientDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    repository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Ingredient> findByName(String name) {
    var ingredient = repository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "name", name));
    return new ResponseEntity<>(ingredient, HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<Ingredient> findById(Long id) {
    var ingredient = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
    return new ResponseEntity<>(ingredient, HttpStatus.FOUND);
  }

  private void validateUpdateRequest(Ingredient newIngredient) {
    repository.findById(newIngredient.getId())
        .orElseThrow(() ->
            new ResourceNotFoundException(resourceClassName, "id", String.valueOf(newIngredient.getId()))
        );
    var optionalIngredient = repository.findByName(newIngredient.getName());
    if (optionalIngredient.isPresent() && !optionalIngredient.get().getId().equals(newIngredient.getId())) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", newIngredient.getName());
    }
  }
}
