package com.example.mealplanner.helpers.validators;

import com.example.mealplanner.dto.DishMealtimeDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.composite.DishMealtime;
import com.example.mealplanner.repositories.DishMealtimeRepo;
import org.springframework.stereotype.Service;

@Service
public class DishMealtimeDtoRequestValidator extends AbstractCRUDRequestValidator<DishMealtimeDto> {
  private final static String resourceClassName = DishMealtime.class.getName();
  private final static String resourceClassSimpleName = DishMealtime.class.getSimpleName();
  private final DishMealtimeRepo repository;


  public DishMealtimeDtoRequestValidator(DishMealtimeRepo repository) {
    super(resourceClassSimpleName);
    this.repository = repository;
  }
  @Override
  public void validateSaveRequest(DishMealtimeDto dishMealtimeDto) {
    validateIdForSaveRequest(dishMealtimeDto.getId());
    validateAmount(dishMealtimeDto.getDishAmount());
  }

  @Override
  public void validateUpdateRequest(DishMealtimeDto dishMealtimeDto) {
    validateIdForUpdateOrDeleteRequest(dishMealtimeDto.getId());
    validateAmount(dishMealtimeDto.getDishAmount());
  }

  @Override
  public void validateDeleteRequest(Long id) {
    validateIdForUpdateOrDeleteRequest(id);
  }

  private void validateIdForUpdateOrDeleteRequest(Long id) {
    if (id == null) {
      throw new IllegalArgumentException(resourceClassSimpleName + "'s id should not be null.");
    }
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
  }
}
