package com.example.mealplanner.helpers.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum AmountType {
  KILOGRAMS("kg"),
  GRAMS("g"),
  LITRES("l"),
  PIECES("pcs");

  private final String code;
}
