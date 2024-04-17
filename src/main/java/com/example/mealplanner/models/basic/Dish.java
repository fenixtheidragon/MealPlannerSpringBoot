package com.example.mealplanner.models.basic;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import com.example.mealplanner.models.composite.DishToMealRelation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="dishes")
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long dishId;

  @Column
  @NonNull
  private String name = "";

  @Column(columnDefinition = "TEXT")
  private String recipe = "";

  @Column
  private int availableAmount = 0;

  @Column
  private AmountType amountType = AmountType.GRAMS;

  @OneToMany(mappedBy = "dish" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<DishToIngredientRelation> dishToIngredientRelations = new HashSet<>();

  @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<DishToMealRelation> dishToMealRelations = new HashSet<>();

  public boolean isAvailable() {
    return availableAmount > 0;
  }

  public void addDishToIngredientRelation(DishToIngredientRelation relation) {
    this.dishToIngredientRelations.add(relation);
  }
}
