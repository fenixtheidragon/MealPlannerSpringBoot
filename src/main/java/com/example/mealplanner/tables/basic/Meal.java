package com.example.mealplanner.tables.basic;

import com.example.mealplanner.tables.composite.Menu;
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

  @OneToMany(mappedBy = "meal")
  private Set<Menu> menu;

  @ManyToMany(mappedBy = "mealsForTheDay")
  private List<Day> days;
}
