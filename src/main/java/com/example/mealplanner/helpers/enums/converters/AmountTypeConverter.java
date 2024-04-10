package com.example.mealplanner.helpers.enums.converters;

import com.example.mealplanner.helpers.enums.AmountType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AmountTypeConverter implements AttributeConverter<AmountType, String> {

  @Override
  public String convertToDatabaseColumn(AmountType amountType) {
    if (amountType == null) {
      return null;
    }
    return amountType.getCode();
  }

  @Override
  public AmountType convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return Stream.of(AmountType.values())
        .filter(amountType -> amountType.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
