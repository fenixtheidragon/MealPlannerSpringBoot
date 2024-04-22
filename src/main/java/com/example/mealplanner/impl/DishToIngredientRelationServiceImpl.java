package com.example.mealplanner.impl;

import com.example.mealplanner.dto.IngredientForRecipeDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.models.composite.DishToIngredientRelation;
import com.example.mealplanner.repositories.DishRepository;
import com.example.mealplanner.repositories.DishToIngredientRelationRepository;
import com.example.mealplanner.repositories.IngredientRepository;
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

  private final IngredientRepository ingredientRepository;
  private final DishRepository dishRepository;
  private final DishToIngredientRelationRepository dToIRelationRepository;

  @Override
  public ResponseEntity<RecipeDto> getRecipeDtoByDishId(Long dishId) {
    var dish = validateAndReturnDish(dishId);
    var recipeDto = getRecipe(dish);
    return new ResponseEntity<>(recipeDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<DishToIngredientRelation>> saveIngredientToDishRelations(
      List<IngredientForRecipeDto> ingredientForRecipeDtoList, Long dishId
  ) {
    var dish = validateAndReturnDish(dishId);
    var relationList = new ArrayList<DishToIngredientRelation>();
    ingredientForRecipeDtoList.forEach(ingredientDTO -> {
      var relation = new DishToIngredientRelation();
      relation.setDish(dish);
      var ingredient = ingredientRepository.findById(ingredientDTO.getIngredientId())
          .orElseThrow(() ->
              new ResourceNotFoundException(
                  Ingredient.class.getName(), "id", String.valueOf(ingredientDTO.getIngredientId())
              )
          );
      relation.setIngredient(ingredient);
      relation.setAmountOfIngredient(ingredientDTO.getAmountOfIngredient());
      relation.setAmountType(ingredientDTO.getAmountType());
      dToIRelationRepository.save(relation);
      relationList.add(relation);
    });
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

  private Dish validateAndReturnDish(Long dishId) {
    return dishRepository.findById(dishId)
        .orElseThrow(() ->
            new ResourceNotFoundException(Dish.class.getName(), "id", String.valueOf(dishId))
        );
  }

  private RecipeDto getRecipe(Dish dish) {
    var ingredientForRecipeDtoList = getIngredientForRecipeDtoList(dish);
    var recipeDto = new RecipeDto();
    recipeDto.setDishId(dish.getId());
    recipeDto.setDishName(dish.getName());
    recipeDto.setIngredientForRecipeDtoList(ingredientForRecipeDtoList);
    return recipeDto;
  }

  private List<IngredientForRecipeDto> getIngredientForRecipeDtoList(Dish dish) {
    var ingredientForRecipeDtoList = new ArrayList<IngredientForRecipeDto>();
    dish.getDishToIngredientRelations().forEach(relation -> {
          var ingredientForRecipeDto = new IngredientForRecipeDto();
          var ingredient = relation.getIngredient();
          ingredientForRecipeDto.setIngredientId(ingredient.getId());
          ingredientForRecipeDto.setIngredientName(ingredient.getName());
          ingredientForRecipeDto.setAmountOfIngredient(relation.getAmountOfIngredient());
          ingredientForRecipeDto.setAmountType(relation.getAmountType());
          ingredientForRecipeDtoList.add(ingredientForRecipeDto);
        }
    );
    return ingredientForRecipeDtoList;
  }
}
