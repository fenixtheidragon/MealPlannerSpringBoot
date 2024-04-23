package com.example.mealplanner.impl;

import com.example.mealplanner.dto.IngredientDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.models.basic.Ingredient;
import com.example.mealplanner.models.composite.DishIngredient;
import com.example.mealplanner.repositories.DishRepo;
import com.example.mealplanner.repositories.DishIngredientRepo;
import com.example.mealplanner.repositories.IngredientRepo;
import com.example.mealplanner.services.DishIngredientService;
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
public class DishIngredientServiceImpl implements DishIngredientService {

  private final IngredientRepo ingredientRepository;
  private final DishRepo dishRepository;
  private final DishIngredientRepo dToIRelationRepository;

  @Override
  public ResponseEntity<RecipeDto> getRecipeDtoByDishId(Long dishId) {
    var dish = validateAndReturnDish(dishId);
    var recipeDto = getRecipe(dish);
    return new ResponseEntity<>(recipeDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<DishIngredient>> saveIngredientToDishRelations(
      List<IngredientDto> ingredientForRecipeDtoList, Long dishId
  ) {
    var dish = validateAndReturnDish(dishId);
    var relationList = new ArrayList<DishIngredient>();
    ingredientForRecipeDtoList.forEach(ingredientDTO -> {
      var relation = new DishIngredient();
      relation.setDish(dish);
      var ingredient = ingredientRepository.findById(ingredientDTO.getId())
          .orElseThrow(() ->
              new ResourceNotFoundException(
                  Ingredient.class.getName(), "id", String.valueOf(ingredientDTO.getId())
              )
          );
      relation.setIngredient(ingredient);
      relation.setAmountOfIngredient(ingredientDTO.getAmount());
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
  public ResponseEntity<List<DishIngredient>> findAll() {
    var dishIngredient
    dToIRelationRepository.findAll().stream()
    return new ResponseEntity<>(, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<DishIngredient> save(DishIngredient relation) {
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
  public ResponseEntity<DishIngredient> update(DishIngredient relation) {
    return null;
  }

  @Override
  public ResponseEntity<HttpStatus> deleteById(Long id) {
    return null;
  }

  @Override
  public ResponseEntity<DishIngredient> findById(Long id) {
    var relation = dToIRelationRepository.findById(id)
        .orElseThrow(() ->
            new ResourceNotFoundException(DishIngredient.class.getName(), "id", String.valueOf(id))
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
    recipeDto.setIngredientDtoList(ingredientForRecipeDtoList);
    return recipeDto;
  }

  private List<IngredientDto> getIngredientForRecipeDtoList(Dish dish) {
    var ingredientForRecipeDtoList = new ArrayList<IngredientDto>();
    dish.getDishToIngredientRelations().forEach(relation -> {
          var ingredientForRecipeDto = new IngredientDto();
          var ingredient = relation.getIngredient();
          ingredientForRecipeDto.setId(ingredient.getId());
          ingredientForRecipeDto.setName(ingredient.getName());
          ingredientForRecipeDto.setAmount(relation.getAmountOfIngredient());
          ingredientForRecipeDto.setAmountType(relation.getAmountType());
          ingredientForRecipeDtoList.add(ingredientForRecipeDto);
        }
    );
    return ingredientForRecipeDtoList;
  }
}
