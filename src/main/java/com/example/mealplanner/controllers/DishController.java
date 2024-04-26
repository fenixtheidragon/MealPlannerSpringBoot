package com.example.mealplanner.controllers;

import com.example.mealplanner.dto.DishDto;
import com.example.mealplanner.dto.DishIngredientDto;
import com.example.mealplanner.dto.RecipeDto;
import com.example.mealplanner.helpers.exceptions.ResourceNotFoundException;
import com.example.mealplanner.models.basic.Dish;
import com.example.mealplanner.services.DishIngredientService;
import com.example.mealplanner.services.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dishes")
public class DishController extends GeneralController<DishDto> {
  private final DishService dishService;
  private final DishIngredientService dishIngredientService;

  public DishController(DishService dishService, DishIngredientService dishIngredientService) {
    super(dishService);
    this.dishService = dishService;
    this.dishIngredientService = dishIngredientService;
  }

  @GetMapping("/names/{name}")
  public ResponseEntity<DishDto> findByName(@PathVariable String name) {
    return dishService.findByName(name);
  }

  @GetMapping("/{id}/recipe")
  public ResponseEntity<RecipeDto> getRecipe(@PathVariable Long dishId) {
    return getRecipeByDishId(dishId);
  }

  @PostMapping("/{id}/recipe")
  public ResponseEntity<RecipeDto> saveRecipe(@PathVariable Long dishId, @RequestBody RecipeDto recipeDto) {
    recipeDto.getIngredientDtoList().forEach(ingredientDto -> {
      var dishIngredientDto = new DishIngredientDto();
      dishIngredientDto.setDishId(dishId);
      dishIngredientDto.setIngredientId(ingredientDto.getId());
      dishIngredientDto.setIngredientAmount(ingredientDto.getAmount());
      dishIngredientDto.setAmountType(ingredientDto.getAmountType());
      dishIngredientService.save(dishIngredientDto);
    });
    return ResponseEntity.ok(recipeDto);
  }

  private ResponseEntity<RecipeDto> getRecipeByDishId(Long dishId) {
    var dishDto = dishService.findById(dishId).getBody();
    if (dishDto == null) {
      throw new ResourceNotFoundException(DishDto.class.getSimpleName(), "id", String.valueOf(dishId));
    }
    var dish = new Dish(dishDto);
    return new ResponseEntity<>(getRecipeDtoByDish(dish), HttpStatus.FOUND);
  }

  private RecipeDto getRecipeDtoByDish(Dish dish) {
    var recipeDto = new RecipeDto(dish);
    var ingredientDtoList = dishIngredientService
        .findIngredientDtoListByDishId(dish.getId())
        .getBody();
    recipeDto.setIngredientDtoList(ingredientDtoList);
    return recipeDto;
  }
}
