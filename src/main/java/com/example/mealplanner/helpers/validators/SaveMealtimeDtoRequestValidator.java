package com.example.mealplanner.helpers.validators;

import com.example.mealplanner.dto.MealtimeDto;

public class SaveMealtimeDtoRequestValidator {

  public boolean isValid(MealtimeDto mealtimeDto) {
    var id = mealtimeDto.getId();
    var day = mealtimeDto.getDay();
    var category = mealtimeDto.getCategory();
    return (id != null) && (id > 0) &&
        (day != null) &&
        (category != null);
  }
}
