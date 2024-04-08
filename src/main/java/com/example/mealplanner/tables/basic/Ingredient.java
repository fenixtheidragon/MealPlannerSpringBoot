package com.example.mealplanner.tables.basic;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.tables.composite.Recipe;
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
  private int amount;

  @Column
  @NonNull
  private AmountType amountType;

  @OneToMany(mappedBy = "ingredient")
  private Set<Recipe> recipes;
}
