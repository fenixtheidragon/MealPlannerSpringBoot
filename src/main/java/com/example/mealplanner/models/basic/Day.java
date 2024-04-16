package com.example.mealplanner.models.basic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.DayOfWeek;
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

  @Column(unique = true)
  @NonNull
  private DayOfWeek name;

  @Column(columnDefinition = "TEXT")
  private String notes = "";

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "days_meals",
      joinColumns = @JoinColumn(name = "day_id"),
      inverseJoinColumns = @JoinColumn(name="meal_id")
  )
  private List<Meal> meals;
}
