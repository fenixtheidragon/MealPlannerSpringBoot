package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.*;
import com.example.mealplanner.models.basic.Mealtime;
import com.example.mealplanner.services.DishMealtimeService;
import com.example.mealplanner.services.MealtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/mealtimes")
public class MealtimeController extends GeneralController<MealtimeDto> {
  private final MealtimeService mealtimeService;
  private final DishMealtimeService dishMealtimeService;

  public MealtimeController(MealtimeService mealtimeService, DishMealtimeService dishMealtimeService) {
    super(mealtimeService);
    this.mealtimeService = mealtimeService;
    this.dishMealtimeService = dishMealtimeService;
  }

  @GetMapping("/{id}/mealcourse")
  public ResponseEntity<MealCourseDto> getMealCourse(@PathVariable Long id) {
    var mealtimeDto = mealtimeService.findById(id).getBody();
    if (mealtimeDto == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    var mealtime = new Mealtime(mealtimeDto);
    var mealCourseDto = new MealCourseDto(mealtime);
    var dishDtoList = dishMealtimeService.findDishDtoListByMealtimeId(mealtime.getId()).getBody();
    mealCourseDto.setDishDtoList(dishDtoList);
    return new ResponseEntity<>(mealCourseDto, HttpStatus.FOUND);
  }

  @PostMapping("/{id}/mealcourse")
  public ResponseEntity<MealCourseDto> saveMealCourse(@PathVariable Long id, @RequestBody MealCourseDto mealCourseDto) {
    mealCourseDto.getDishDtoList().forEach(dishDto -> {
      var dishMealtimeDto = new DishMealtimeDto();
      dishMealtimeDto.setMealtimeId(mealCourseDto.getMealtimeId());
      dishMealtimeDto.setDishId(dishDto.getId());
      dishMealtimeDto.setDishAmount(dishDto.getAmount());
      dishMealtimeDto.setAmountType(dishDto.getAmountType());
      dishMealtimeService.save(dishMealtimeDto);
    });
    return ResponseEntity.ok(mealCourseDto);
  }
}
