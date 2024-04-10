package com.example.mealplanner.repositories;

import com.example.mealplanner.models.composite.DishToIngredientRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishToIngredientRelationRepository extends JpaRepository<DishToIngredientRelation,Long> {
}
