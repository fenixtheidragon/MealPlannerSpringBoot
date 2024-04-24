package com.example.mealplanner.helpers.validators;

import com.example.mealplanner.dto.MealtimeDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Mealtime;
import com.example.mealplanner.repositories.MealtimeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MealtimeDtoRequestValidator implements CRUDRequestValidator<MealtimeDto> {
  private final MealtimeRepo repository;
  private final static String resourceClassName = Mealtime.class.getName();
  @Override
  public void validateSaveRequest(MealtimeDto mealtimeDto) {
    if (mealtimeDto.getId() != null) {
      throw new IllegalArgumentException("Mealtime's id should be null.");
    }
  }

  @Override
  public void validateUpdateRequest(MealtimeDto mealtimeDto) {
    var id = mealtimeDto.getId();
    if (id == null) {
      throw new IllegalArgumentException("Mealtime's id shouldn't be null.");
    }
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(resourceClassName, "id", String.valueOf(id)));
  }

  @Override
  public void validateDeleteRequest(Long id) {

  }
}
