package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long> {

}
