package com.example.mealplanner.models.basic;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishIngredient;
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
  private Long id;

  @Column(unique = true)
  @NonNull
  private String name = "";

  @Column
  @NonNull
  private int availableAmount = 0;

  @Column
  @NonNull
  private AmountType amountType = AmountType.GRAMS;

  @JsonIgnore
  @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<DishIngredient> dishToIngredientRelations = new HashSet<>();

  public Ingredient(IngredientDto ingredientDto) {
    this.id = ingredientDto.getId();
    this.name = ingredientDto.getName();
    this.availableAmount = ingredientDto.getAmount();
    this.amountType = ingredientDto.getAmountType();
  }
  public boolean isAvailable() {
    return availableAmount > 0;
  }

  public void addDishToIngredientRelation(DishIngredient relation) {
    this.dishToIngredientRelations.add(relation);
  }
}
