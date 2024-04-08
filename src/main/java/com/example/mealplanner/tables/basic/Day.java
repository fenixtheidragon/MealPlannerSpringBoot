package com.example.mealplanner.tables.basic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@Table(name = "days")
@AllArgsConstructor
@NoArgsConstructor
public class Day {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  @NonNull
  private String name;

  @Column
  private String description;

  @ManyToMany
  @JoinTable(
      name = "meals_for_the_day",
      joinColumns = @JoinColumn(name = "day_id"),
      inverseJoinColumns = @JoinColumn(name="meal_id")
  )
  private List<Meal> mealsForTheDay;
}
