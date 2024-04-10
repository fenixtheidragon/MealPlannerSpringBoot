package com.example.mealplanner.repositories;

import com.example.mealplanner.models.composite.DishToMealRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishToMealRelationRepository extends JpaRepository<DishToMealRelation, Long> {
}
