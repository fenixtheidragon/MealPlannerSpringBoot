package com.example.mealplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralRepo<T> extends JpaRepository<T,Long> {
}
