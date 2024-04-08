package com.example.mealplanner.tables.composite.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class RecipeKey implements Serializable {
  @Column(name="dish_id")
  private Long dishId;

  @Column(name="ingredient_id")
  private Long ingredientId;
}
