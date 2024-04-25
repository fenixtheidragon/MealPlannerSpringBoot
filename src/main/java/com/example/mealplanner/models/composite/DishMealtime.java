package com.example.mealplanner.models.composite;

import com.example.mealplanner.helpers.enums.AmountType;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Mealtime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Table(name = "dishes_mealtimes")
@AllArgsConstructor
@NoArgsConstructor
public class DishMealtime {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  private int dishAmount = 0;

  @Column
  @NonNull
  private AmountType amountType = AmountType.GRAMS;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mealtime_id")
  private Mealtime mealtime;
}
