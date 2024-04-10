package com.example.mealplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mealplanner.models.basic.Day;

public interface DayRepository extends JpaRepository<Day, Long> {
}
