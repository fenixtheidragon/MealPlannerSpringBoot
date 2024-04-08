package com.example.mealplanner.helpers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MealCategory {
  UNCATEGORIZED("uncategorized", "0"),
  BREAKFAST("breakfast", "1"),
  SECOND_BREAKFAST("second breakfast", "2"),
  LUNCH("lunch", "3"),
  TEA("tea", "4"),
  DINNER("dinner", "5"),
  SUPPER("supper", "6"),
  SNACK("snack", "7");

  private final String name;
  private final String number;

  public static MealCategory getMealCategoryByInput(String input) {
    for (var type : MealCategory.values()) {
      if (type.getNumber().equals(input)) {
        return type;
      }
    }
    return MealCategory.UNCATEGORIZED;
  }

  public static String getDescription() {
    var sb = new StringBuilder();
    for (var MealCategory : MealCategory.values()) {
      sb.append(MealCategory.getNumber())
          .append(". ").
          append(MealCategory.getName()).
          append(";")
          .append(System.lineSeparator());
    }
    return sb.delete(sb.length() - 1, sb.length()).toString();
  }
}
