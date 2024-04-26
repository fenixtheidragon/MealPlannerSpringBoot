package com.example.mealplanner.models.basic;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishIngredient;
import com.example.mealplanner.models.composite.DishMealtime;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private Long id;

  @Column
  @NonNull
  private String name = "";

  @Column(columnDefinition = "TEXT")
  private String recipeDescription = "";

  @Column
  private int availableAmount = 0;

  @Column
  private AmountType amountType = AmountType.GRAMS;

  @JsonIgnore
  @OneToMany(mappedBy = "dish" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<DishIngredient> dishIngredientSet = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<DishMealtime> dishMealtimeSet = new HashSet<>();

  public Dish(DishDto dishDto) {
    this.id = dishDto.getId();
    this.name = dishDto.getName();
    this.recipeDescription = dishDto.getRecipeDescription();
    this.availableAmount = dishDto.getAmount();
    this.amountType = dishDto.getAmountType();
  }

  public boolean isAvailable() {
    return availableAmount > 0;
  }
}
