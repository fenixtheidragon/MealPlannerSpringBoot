package com.example.mealplanner.models.basic;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import com.example.mealplanner.models.composite.DishToMealRelation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity
@Data
@Table(name="dishes")
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  @NonNull
  private String name;

  @Column(columnDefinition = "TEXT")
  private String recipe;

  @Column
  private int availableAmount;

  @Column
  private AmountType amountType;

  @OneToMany(mappedBy = "dish" , fetch = FetchType.LAZY)
  private Set<DishToIngredientRelation> dishToIngredientRelations;

  @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY)
  private Set<DishToMealRelation> dishToMealRelations;

  public boolean isAvailable() {
    return availableAmount > 0;
  }
}
