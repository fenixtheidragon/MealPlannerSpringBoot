package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import lombok.Data;

@Data
public class IngredientDto {
  private String name;
  private int availableAmount;
  private AmountType amountType;
}
