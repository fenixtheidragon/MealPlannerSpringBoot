package com.example.mealplanner.impl;

import com.example.mealplanner.dto.MealtimeDto;
import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Mealtime;
import com.example.mealplanner.repositories.MealtimeRepo;
import com.example.mealplanner.services.MealtimeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class MealtimeServiceImpl implements MealtimeService {
  private final MealtimeRepo repository;
  private final static String resourceClassName = Mealtime.class.getSimpleName();

  @Override
  public ResponseEntity<List<MealtimeDto>> findAll() {
    List<MealtimeDto> mealtimeDtoList = new ArrayList<>();
    repository.findAll().forEach(mealtime -> {
      var mealtimeDto = new MealtimeDto(mealtime);
      mealtimeDtoList.add(mealtimeDto);
    });
    return new ResponseEntity<>(mealtimeDtoList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MealtimeDto> save(MealtimeDto mealtimeDto) {
    var optionalMeal = repository.findById(mealtimeDto.getId());
    if (optionalMeal.isPresent()) {
      throw new ResourceAlreadyExistsException(resourceClassName, "id", String.valueOf(mealtimeDto.getId()));
    }
    var mealtime = new Mealtime(mealtimeDto);
    repository.save(mealtime);
    return new ResponseEntity<>(mealtimeDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MealtimeDto> update(MealtimeDto newMealtimeDto) {

    return new ResponseEntity<>(repository.save(newMealtime), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Mealtime.class.getName(), "id", String.valueOf(id)));
    repository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mealtime> findById(Long id) {
    var meal = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Mealtime.class.getName(), "id", String.valueOf(id)));
    return new ResponseEntity<>(meal, HttpStatus.FOUND);
  }
}
