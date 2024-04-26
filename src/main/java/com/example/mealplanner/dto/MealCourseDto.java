package com.example.mealplanner.dto;

import com.example.mealplanner.models.basic.Mealtime;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class MealCourseDto {
  private Long mealtimeId;
  private List<DishDto> dishDtoList;

  public MealCourseDto(Mealtime mealtime) {
    this.mealtimeId = mealtime.getId();
    this.dishDtoList = extractIngredientDtoListFrom(mealtime);
  }

  private static List<DishDto> extractIngredientDtoListFrom(Mealtime mealtime) {
    var dishDtoList = new ArrayList<DishDto>();
    mealtime.getDishMealtimeSet().forEach(dishMealtime -> {
      var dishDto = new DishDto(dishMealtime.getDish());
      dishDtoList.add(dishDto);
    });
    return dishDtoList;
  }
}
