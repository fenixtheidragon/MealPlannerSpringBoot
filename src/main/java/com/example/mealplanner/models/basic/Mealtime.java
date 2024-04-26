package com.example.mealplanner.models.basic;

import com.example.mealplanner.dto.MealtimeDto;
import com.example.mealplanner.models.composite.DishMealtime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.mealplanner.helpers.enums.MealCategory;
import org.springframework.lang.NonNull;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Table(name = "meals")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mealtime {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  @NonNull
  private DayOfWeek day = DayOfWeek.MONDAY;

  @Column
  @NonNull
  private MealCategory category = MealCategory.BREAKFAST;

  @JsonIgnore
  @OneToMany(mappedBy = "mealtime", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<DishMealtime> dishMealtimeSet;

  public Mealtime(MealtimeDto mealtimeDto) {
    this.id = mealtimeDto.getId();
    this.day = mealtimeDto.getDay();
    this.category = mealtimeDto.getCategory();
    this.dishMealtimeSet = new HashSet<>();
  }
}
