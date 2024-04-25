package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishMealtime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class DishMealtimeDto {
  private Long id;
  private Long dishId;
  private Long mealtimeId;
  private Integer dishAmount;
  private AmountType amountType;
  public DishMealtimeDto(DishMealtime dishMealtime) {
    this.id = dishMealtime.getId();
    this.dishId = dishMealtime.getDish().getId();
    this.mealtimeId = dishMealtime.getMealtime().getId();
    this.dishAmount = dishMealtime.getDishAmount();
    this.amountType = dishMealtime.getAmountType();
  }
}
