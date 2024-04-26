package com.example.mealplanner.dto;

import com.example.mealplanner.helpers.enums.MealCategory;
import com.example.mealplanner.models.basic.Mealtime;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@NoArgsConstructor
public class MealtimeDto {
  private Long id;
  private DayOfWeek day = DayOfWeek.MONDAY;
  private MealCategory category = MealCategory.BREAKFAST;

  public MealtimeDto(Mealtime mealtime) {
    this.id = mealtime.getId();
    this.day = mealtime.getDay();
    this.category = mealtime.getCategory();
  }
}
