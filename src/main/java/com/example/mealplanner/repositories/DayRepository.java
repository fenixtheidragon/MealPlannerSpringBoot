package com.example.mealplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mealplanner.models.basic.Day;

import java.time.DayOfWeek;
import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
  Optional<Day> findByName(DayOfWeek name);
}
