package com.example.mealplanner.impl;

import com.example.mealplanner.helpers.exceptions.ResourceAlreadyExistsException;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import com.example.mealplanner.repositories.DishRepository;
import com.example.mealplanner.repositories.DishToIngredientRelationRepository;
import com.example.mealplanner.services.DishToIngredientRelationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class DishToIngredientRelationServiceImpl implements DishToIngredientRelationService {

  private final DishRepository dishRepository;
  private final DishToIngredientRelationRepository dToIRelationRepository;

  @Override
  public ResponseEntity<List<Ingredient>> findIngredientsByDishId(Long dishId) {
    var dish = validateDish(dishId);
    var ingredientList = new ArrayList<Ingredient>();
    dish.getDishToIngredientRelations().forEach(a -> ingredientList.add(a.getIngredient()));
    return new ResponseEntity<>(ingredientList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<DishToIngredientRelation>> saveIngredientsToDishRelations(
      List<DishToIngredientRelation> relationList, Long dishId
  ) {
    relationList.forEach(dToIRelationRepository::save);
    return new ResponseEntity<>(relationList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Ingredient>> updateIngredientsToDishRelations(List<Ingredient> ingredientList, Long dishId) {
    return null;
  }

  @Override
  public ResponseEntity<HttpStatus> deleteIngredientsToDishRelations(List<Long> ingredientIdList, Long dishId) {
    return null;
  }

  private Dish validateDish(Long dishId) {
    return dishRepository.findById(dishId)
        .orElseThrow(() ->
            new ResourceNotFoundException(Dish.class.getName(), "id", String.valueOf(dishId))
        );
  }

  @Override
  public ResponseEntity<List<DishToIngredientRelation>> findAll() {
    return new ResponseEntity<>(dToIRelationRepository.findAll(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<DishToIngredientRelation> save(DishToIngredientRelation relation) {
    /*dToIRelationRepository.findById(
        relation.getId().getDishId(), relation.getId().getIngredientId()
    ).orElseThrow(() ->
        new ResourceAlreadyExistsException(
            DishToIngredientRelation.class.getName(), "id", String.valueOf(relation.getId())
        )
    );*/
    return new ResponseEntity<>(dToIRelationRepository.save(relation), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<DishToIngredientRelation> update(DishToIngredientRelation relation) {
    return null;
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    return null;
  }

  @Override
  public ResponseEntity<DishToIngredientRelation> findById(Long id) {
    var relation = dToIRelationRepository.findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException(DishToIngredientRelation.class.getName(), "id", String.valueOf(id))
        );
    return new ResponseEntity<>(relation, HttpStatus.FOUND);
  }
}
