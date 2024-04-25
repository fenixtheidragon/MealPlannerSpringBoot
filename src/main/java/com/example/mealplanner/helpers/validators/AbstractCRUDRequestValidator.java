package com.example.mealplanner.helpers.validators;

public abstract class AbstractCRUDRequestValidator<T> implements CRUDRequestValidator<T> {
  private final String className;

  public AbstractCRUDRequestValidator(String className) {
    this.className = className;
  }

  protected void checkIfNameIsEmpty(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException(className + "'s name shouldn't be empty");
    }
  }

  protected void validateAmount(Integer amount) {
    if (amount == null || amount < 0) {
      throw new IllegalArgumentException(className + "'s amount should be equal or more than zero.");
    }
  }

  protected void validateIdForSaveRequest(Long id) {
    if (id != null) {
      throw new IllegalArgumentException(className + "'s id should be null in save request.");
    }
  }
}
