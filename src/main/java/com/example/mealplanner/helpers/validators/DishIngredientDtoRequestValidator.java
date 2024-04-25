package com.example.mealplanner.helpers.validators;

import com.example.mealplanner.dto.DishIngredientDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.composite.DishIngredient;
import com.example.mealplanner.repositories.DishIngredientRepo;
import org.springframework.stereotype.Service;

@Service
public class DishIngredientDtoRequestValidator extends AbstractCRUDRequestValidator<DishIngredientDto> {
  private final static String resourceClassName = DishIngredient.class.getName();
  private final static String resourceClassSimpleName = DishIngredient.class.getSimpleName();
  private final DishIngredientRepo repository;


  public DishIngredientDtoRequestValidator(DishIngredientRepo repository) {
    super(resourceClassSimpleName);
    this.repository = repository;
  }
  @Override
  public void validateSaveRequest(DishIngredientDto dishIngredientDto) {
    validateIdForSaveRequest(dishIngredientDto.getId());
    validateAmount(dishIngredientDto.getIngredientAmount());
  }

  @Override
  public void validateUpdateRequest(DishIngredientDto dishIngredientDto) {
    validateIdForUpdateOrDeleteRequest(dishIngredientDto.getId());
    validateAmount(dishIngredientDto.getIngredientAmount());
  }

  @Override
  public void validateDeleteRequest(Long id) {
    validateIdForUpdateOrDeleteRequest(id);
  }

  private void validateIdForUpdateOrDeleteRequest(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Ingredient's id should not be null.");
    }
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
  }
}
