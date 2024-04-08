package com.example.mealplanner.tables.basic;

import com.example.mealplanner.tables.composite.Menu;
import com.example.mealplanner.tables.composite.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity
@Data
@Table(name="dishes")
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  @NonNull
  private String name;

  @Column
  @NonNull
  private String description;

  @OneToMany(mappedBy = "dish")
  private Set<Recipe> recipes;

  @OneToMany(mappedBy = "dish")
  private Set<Menu> menu;
}
