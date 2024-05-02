package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.*;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
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
  public ResponseEntity<MealCourseDto> getMealCourseByMealtimeId(@PathVariable Long mealtimeId) {
    var mealCourseDto = getMealCourseDto(mealtimeId);
    return new ResponseEntity<>(mealCourseDto, HttpStatus.FOUND);
  }

  @PostMapping("/{id}/mealcourse")
  public ResponseEntity<MealCourseDto> saveMealCourse(
      @PathVariable Long mealtimeId, @RequestBody MealCourseDto mealCourseDto
  ) {
    mealCourseDto.getDishDtoList().forEach(dishDto -> {
      var dishMealtimeDto = createDishMealtimeDto(mealtimeId, dishDto);
      dishMealtimeService.save(dishMealtimeDto);
    });
    return ResponseEntity.ok(mealCourseDto);
  }

  private DishMealtimeDto createDishMealtimeDto(Long mealtimeId, DishDto dishDto) {
    var dishMealtimeDto = new DishMealtimeDto();
    dishMealtimeDto.setMealtimeId(mealtimeId);
    dishMealtimeDto.setDishId(dishDto.getId());
    dishMealtimeDto.setDishAmount(dishDto.getAmount());
    dishMealtimeDto.setAmountType(dishDto.getAmountType());
    return dishMealtimeDto;
  }

  private MealCourseDto getMealCourseDto(Long mealtimeId) {
    var mealtime = getMealtime(mealtimeId);
    var mealCourseDto = new MealCourseDto(mealtime);
    var dishDtoList = dishMealtimeService.findDishDtoListByMealtimeId(mealtime.getId()).getBody();
    mealCourseDto.setDishDtoList(dishDtoList);
    return mealCourseDto;
  }

  private Mealtime getMealtime(Long mealtimeId) {
    var mealtimeDto = mealtimeService.findById(mealtimeId).getBody();
    if (mealtimeDto == null) {
      throw new ResourceNotFoundException(Mealtime.class.getSimpleName(), "id", String.valueOf(mealtimeId));
    }
    return new Mealtime(mealtimeDto);
  }
}
