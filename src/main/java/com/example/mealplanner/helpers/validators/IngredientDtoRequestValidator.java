package com.example.mealplanner.helpers.validators;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.repositories.IngredientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class IngredientDtoRequestValidator extends AbstractCRUDRequestValidator<IngredientDto> {
  private final static String resourceClassName = Ingredient.class.getName();
  private final static String resourceClassSimpleName = Ingredient.class.getSimpleName();
  private final IngredientRepo repository;


  public IngredientDtoRequestValidator(IngredientRepo repository) {
    super(resourceClassSimpleName);
    this.repository = repository;
  }
  @Override
  public void validateSaveRequest(IngredientDto ingredientDto) {
    validateIdForSaveRequest(ingredientDto.getId());
    validateAmount(ingredientDto.getAmount());
    validateNameForSaveRequest(ingredientDto.getName());
  }

  @Override
  public void validateUpdateRequest(IngredientDto ingredientDto) {
    var id = ingredientDto.getId();
    validateIdForUpdateOrDeleteRequest(id);
    validateAmount(ingredientDto.getAmount());
    validateNameForUpdateRequest(ingredientDto.getName(), id);
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
      throw new IllegalArgumentException("Ingredient's id should not be null.");
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
