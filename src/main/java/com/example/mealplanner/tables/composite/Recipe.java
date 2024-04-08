package com.example.mealplanner.tables.composite;

import com.example.mealplanner.tables.composite.keys.RecipeKey;
import com.example.mealplanner.tables.basic.Dish;
import com.example.mealplanner.tables.basic.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="recipes")
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
  @EmbeddedId
  RecipeKey id;

  @ManyToOne
  @MapsId("dishId")
  @JoinColumn(name = "dish_id")
  Dish dish;

  @ManyToOne
  @MapsId("ingredientId")
  @JoinColumn(name="ingredient_id")
  Ingredient ingredient;

  int amount;
}
