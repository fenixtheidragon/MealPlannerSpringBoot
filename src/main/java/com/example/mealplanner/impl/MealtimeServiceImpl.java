package com.example.mealplanner.impl;

import com.example.mealplanner.dto.MealtimeDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.helpers.validators.MealtimeDtoRequestValidator;
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
  private final MealtimeDtoRequestValidator validator;

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
    validator.validateSaveRequest(mealtimeDto);
    repository.save(new Mealtime(mealtimeDto));
    return new ResponseEntity<>(mealtimeDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MealtimeDto> update(MealtimeDto mealtimeDto) {
    validator.validateUpdateRequest(mealtimeDto);
    repository.save(new Mealtime(mealtimeDto));
    return new ResponseEntity<>(mealtimeDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    validator.validateDeleteRequest(id);
    repository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MealtimeDto> findById(Long id) {
    var mealtime = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(Mealtime.class.getName(), "id", String.valueOf(id)));
    return new ResponseEntity<>(new MealtimeDto(mealtime), HttpStatus.FOUND);
  }
}
