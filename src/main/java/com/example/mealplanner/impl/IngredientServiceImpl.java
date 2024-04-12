package com.example.mealplanner.impl;

import com.example.mealplanner.helpers.exceptions.IngredientNotFoundException;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.repositories.IngredientRepository;
import com.example.mealplanner.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Primary
public class IngredientServiceImpl implements IngredientService {
  private final IngredientRepository repository;

  @Override
  public Ingredient findByName(String name) {
    return repository.findByName(name)
        .orElseThrow(() -> new IngredientNotFoundException("name", name));
  }

  @Override
  public Ingredient findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new IngredientNotFoundException("id", Long.toString(id)));
  }

  @Override
  public List<Ingredient> findAll() {
    return repository.findAll();
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    var optionalIngredient = repository.findByName(ingredient.getName());
    if (optionalIngredient.isPresent()) {
      throw new IllegalArgumentException("Ingredient with name " + ingredient.getName() + " already exists.");
    }
    return repository.save(ingredient);
  }

  @Override
  public Ingredient update(Ingredient ingredient) {
    var ingredientToBeUpdated = repository.findById(ingredient.getId())
        .orElseThrow(() -> new NoSuchElementException("Ingredient with id=" + ingredient.getId() + "was not found."));

    if (ingredient.getName().isEmpty()) {
      throw new IllegalArgumentException("Name of ingredient must not be empty.");
    }
    if (ingredient.getAvailableAmount() < 0) {
      throw new IllegalArgumentException("Available amount of ingredient can not be a negative number.");
    }
    return repository.save(ingredient);
  }

  @Override
  public void deleteByName(String name) {
    var ingredientToBeDeleted = findAll().stream()
        .filter(ingredient -> ingredient.getName().equals(name))
        .findFirst()
        .orElseThrow(NoSuchElementException::new);
    if (ingredientToBeDeleted != null) {
      repository.delete(ingredientToBeDeleted);
    }
  }
}
