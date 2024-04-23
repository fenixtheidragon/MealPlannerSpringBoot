package com.example.mealplanner.helpers.enums.converters;

import com.example.mealplanner.helpers.enums.MealCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MealCategoryConverter implements AttributeConverter<MealCategory, String> {

  @Override
  public String convertToDatabaseColumn(MealCategory mealCategory) {
    if (mealCategory == null) {
      return null;
    }
    return mealCategory.getName();
  }

  @Override
  public MealCategory convertToEntityAttribute(String name) {
    if (name == null) {
      return null;
    }
    return Stream.of(MealCategory.values())
        .filter(mealCategory -> mealCategory.getName().equals(name))
        .findFirst()
        .orElseThrow(()-> new IllegalArgumentException("MealCategory object with name = '" + name + "' doesn't exist."));
  }
}
