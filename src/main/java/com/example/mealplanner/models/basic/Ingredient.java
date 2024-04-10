package com.example.mealplanner.models.basic;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
@Data
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(unique = true)
  @NonNull
  private String name;

  @Column
  private int availableAmount;

  @Column
  @NonNull
  private AmountType amountType;

  @OneToMany(mappedBy = "ingredient")
  private Set<DishToIngredientRelation> dishToIngredientRelations;

  public boolean isAvailable() {
    return availableAmount > 0;
  }
}
