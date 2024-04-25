package com.example.mealplanner.models.composite;

import com.example.mealplanner.dto.DishIngredientDto;
import com.example.mealplanner.helpers.enums.AmountType;
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
public class DishIngredient {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="DishToIngredientRelationId")
  private Long id;

  @Column
  int ingredientAmount = 0;

  @Column
  @NonNull
  private AmountType amountType = AmountType.GRAMS;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="ingredient_id")
  private Ingredient ingredient;

  public DishIngredient(DishIngredientDto dishIngredientDto, Dish dish, Ingredient ingredient) {
    this.id = dishIngredientDto.getId();
    this.ingredientAmount = dishIngredientDto.getIngredientAmount();
    this.amountType = dishIngredientDto.getAmountType();
    this.dish = dish;
    this.ingredient = ingredient;
  }
}
