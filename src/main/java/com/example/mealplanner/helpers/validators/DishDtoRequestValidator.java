package com.example.mealplanner.helpers.validators;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.repositories.DishRepo;
import org.springframework.stereotype.Service;

@Service
public class DishDtoRequestValidator extends AbstractCRUDRequestValidator<DishDto>{
  private final static String resourceClassName = Dish.class.getName();
  private final static String resourceClassSimpleName = Dish.class.getSimpleName();
  private final DishRepo repository;

  public DishDtoRequestValidator(DishRepo repository) {
    super(resourceClassSimpleName);
    this.repository = repository;
  }

  @Override
  public void validateSaveRequest(DishDto dishDto) {
    validateIdForSaveRequest(dishDto.getId());
    validateAmount(dishDto.getAmount());
    validateNameForSaveRequest(dishDto.getName());
  }

  @Override
  public void validateUpdateRequest(DishDto dishDto) {
    var id = dishDto.getId();
    validateIdForUpdateOrDeleteRequest(id);
    validateAmount(dishDto.getAmount());
    validateNameForUpdateRequest(dishDto.getName(), id);
  }

  @Override
  public void validateDeleteRequest(Long id) {
    validateIdForUpdateOrDeleteRequest(id);
  }

  private void validateNameForUpdateRequest(String name, Long id) {
    checkIfNameIsEmpty(name);
    var optionalIngredient = repository.findByName(name);
    if (optionalIngredient.isPresent() && !optionalIngredient.get().getId().equals(id)) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", name);
    }
  }

  private void validateIdForUpdateOrDeleteRequest(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Dish's id should not be null.");
    }
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
  }

  private void validateNameForSaveRequest(String name) {
    checkIfNameIsEmpty(name);
    var optionalIngredient = repository.findByName(name);
    if (optionalIngredient.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "name", name);
    }
  }
}
