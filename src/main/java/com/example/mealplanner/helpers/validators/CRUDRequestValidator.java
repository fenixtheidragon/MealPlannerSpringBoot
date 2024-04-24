package com.example.mealplanner.helpers.validators;

public interface CRUDRequestValidator<T> {

  void validateSaveRequest(T t);
  void validateUpdateRequest(T t);
  void validateDeleteRequest(Long id);

}
