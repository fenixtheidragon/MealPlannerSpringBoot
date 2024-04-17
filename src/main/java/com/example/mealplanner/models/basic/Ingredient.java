package com.example.mealplanner.models.basic;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long ingredientId;

  @Column(unique = true)
  @NonNull
  private String name = "";

  @Column
  @NonNull
  private int availableAmount = 0;

  @Column
  @NonNull
  private AmountType amountType = AmountType.GRAMS;


  @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<DishToIngredientRelation> dishToIngredientRelations = new HashSet<>();

  public boolean isAvailable() {
    return availableAmount > 0;
  }

  public void addDishToIngredientRelation(DishToIngredientRelation relation) {
    this.dishToIngredientRelations.add(relation);
  }
}
