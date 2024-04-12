package com.example.mealplanner.impl;

import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.repositories.IngredientRepository;
import com.example.mealplanner.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Primary
public class IngredientServiceImpl implements IngredientService {
  private final IngredientRepository repository;

  @Override
  public ResponseEntity<Ingredient> findByName(String name) {
     return repository.findByName(name)
         .orElseThrow(Res);
     return createResponseEntityFrom(optionalIngredient);
  }

  @Override
  public ResponseEntity<Ingredient> findById(Long id) {
    var optionalIngredient = repository.findById(id);
    return createResponseEntityFrom(optionalIngredient);
  }

  @Override
  public ResponseEntity<List<Ingredient>> findAll() {
    var optionalIngredientsList = repository.findAll();
    if (!optionalIngredientsList.isEmpty()) {
      return new ResponseEntity<>(repository.findAll(), HttpStatus.FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Ingredient> save(Ingredient ingredient) {
    var optionalIngredient = repository.findByName(ingredient.getName());
    if (optionalIngredient.isEmpty()) {
      return new ResponseEntity<>(repository.save(ingredient), HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.CONFLICT);
  }

  @Override
  public ResponseEntity<Ingredient> update(Ingredient ingredient) {
    var ingredientToBeUpdated = repository.findById(ingredient.getId());
    if (ingredientToBeUpdated.isPresent()) {

    }
    return repository.save(ingredient);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteByName(String name) {
    var ingredientToBeDeleted = findAll().stream()
        .filter(ingredient -> ingredient.getName().equals(name))
        .findFirst()
        .orElseThrow(NoSuchElementException::new);
    if (ingredientToBeDeleted != null) {
      repository.delete(ingredientToBeDeleted);
    }
    return null;
  }

  private ResponseEntity<Ingredient> createResponseEntityFrom(Optional<Ingredient> optionalIngredient) {
    if (optionalIngredient.isPresent()) {
      return new ResponseEntity<>(optionalIngredient.get(), HttpStatus.FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
