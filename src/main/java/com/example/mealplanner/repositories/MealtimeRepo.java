package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Mealtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealtimeRepo extends JpaRepository<Mealtime,Long> {
}
