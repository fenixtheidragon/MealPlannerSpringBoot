package com.example.mealplanner.tables.composite;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.tables.composite.keys.MenuKey;
import com.example.mealplanner.tables.basic.Dish;
import com.example.mealplanner.tables.basic.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Table(name = "menus")
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
  @EmbeddedId
  private MenuKey id;

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
