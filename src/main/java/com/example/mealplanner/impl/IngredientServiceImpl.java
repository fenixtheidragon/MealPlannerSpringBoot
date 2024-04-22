package com.example.mealplanner.impl;

import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.repositories.IngredientRepository;
import com.example.mealplanner.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class IngredientServiceImpl implements IngredientService {
  private final static String resourceClassName = Ingredient.class.getName();
  private final IngredientRepository repository;

  @Override
  public ResponseEntity<List<Ingredient>> findAll() {
    var ingredientList = repository.findAll();
    return new ResponseEntity<>(ingredientList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Ingredient> save(Ingredient ingredient) {
    var optionalIngredient = repository.findByName(ingredient.getName());
    if (optionalIngredient.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", ingredient.getName());
    }
    return new ResponseEntity<>(repository.save(ingredient), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Ingredient> update(Ingredient newIngredient) {
    validateUpdateRequest(newIngredient);
    return new ResponseEntity<>(repository.save(newIngredient), HttpStatus.OK);
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
