package com.example.mealplanner.tables.composite.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class MenuKey implements Serializable {
  @Column(name ="meal_id")
  private Long mealId;
  @Column(name="dish_id")
  private Long dishId;
}
