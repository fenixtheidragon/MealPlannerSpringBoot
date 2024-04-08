package com.example.mealplanner.helpers.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum AmountType {
  KG("kilograms"),
  G("grams"),
  L("litres"),
  PCS("pieces");

  private final String description;
}
