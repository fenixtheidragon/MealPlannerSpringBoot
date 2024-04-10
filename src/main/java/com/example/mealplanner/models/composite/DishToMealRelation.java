package com.example.mealplanner.models.composite;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.keys.DishToMealKey;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Table(name = "dishes_meals")
@AllArgsConstructor
@NoArgsConstructor
public class DishToMealRelation {
  @EmbeddedId
  private DishToMealKey id;

  @ManyToOne
  @MapsId("mealId")
  @JoinColumn(name = "meal_id")
  private Meal meal;

  @ManyToOne
  @MapsId("dishId")
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @Column
  private int amount;

  @Column
  @NonNull
  private AmountType amountType;
}
