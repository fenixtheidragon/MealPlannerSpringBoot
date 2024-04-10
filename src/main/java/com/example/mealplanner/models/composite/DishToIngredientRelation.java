package com.example.mealplanner.models.composite;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.keys.DishToIngredientKey;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name="dishes_ingredients")
@AllArgsConstructor
@NoArgsConstructor
public class DishToIngredientRelation {
  @EmbeddedId
  DishToIngredientKey id;

  @Column
  int amount;

  @Column
  @NonNull
  private AmountType amountType;

  @ManyToOne
  @MapsId("dishId")
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @ManyToOne
  @MapsId("ingredientId")
  @JoinColumn(name="ingredient_id")
  private Ingredient ingredient;
}
