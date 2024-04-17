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
  /*@EmbeddedId
  DishToIngredientKey id;*/

  @Id
  @GeneratedValue
  @Column(name="DishToIngredientRelationId")
  private Long id;

  @Column
  int amountOfIngredient;

  @Column
  @NonNull
  private AmountType amountType;

  @ManyToOne(cascade = CascadeType.ALL)
  //TODO определить fetch.type для many-to-one
  //@MapsId("dishId")
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @ManyToOne
  //@MapsId("ingredientId")
  @JoinColumn(name="ingredient_id")
  private Ingredient ingredient;
}
