package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Mealtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealtimeRepo extends JpaRepository<Mealtime,Long> {
  Optional<Mealtime> findByName(String name);
}
