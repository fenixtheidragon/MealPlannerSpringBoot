package com.example.mealplanner.models.composite.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class DishToMealKey implements Serializable {
  @Column(name="dish_id")
  private Long dishId;
  @Column(name ="meal_id")
  private Long mealId;
}
