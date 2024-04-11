package com.example.mealplanner.models.basic;

import com.example.mealplanner.models.composite.DishToMealRelation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.mealplanner.helpers.enums.MealCategory;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

@Table(name="meals")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  @NonNull
  private MealCategory mealCategory;

  @Column(columnDefinition = "TEXT")
  private String description;

  @OneToMany(mappedBy = "meal",fetch = FetchType.LAZY)
  private Set<DishToMealRelation> dishToMealRelations;

  @ManyToMany(mappedBy = "meals", fetch = FetchType.LAZY)
  private List<Day> days;
}
