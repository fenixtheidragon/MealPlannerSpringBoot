package com.example.mealplanner.repositories;

import com.example.mealplanner.models.basic.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IngredientRepo extends JpaRepository<Ingredient,Long> {
  Optional<Ingredient> findByName(String name);
  @Query(value = "SELECT i FROM Ingredient i WHERE i.name like ?1%")
  List<Ingredient> findByLetters(String letters);
}
