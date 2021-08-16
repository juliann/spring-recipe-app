package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.commands.IngredientCommand;
import com.nadarzy.springrecipeapp.converters.IngredientToIngredientCommand;
import com.nadarzy.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.nadarzy.springrecipeapp.model.Ingredient;
import com.nadarzy.springrecipeapp.model.Recipe;
import com.nadarzy.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

  @Mock RecipeRepository recipeRepository;
  IngredientService ingredientService;
  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  public IngredientServiceImplTest() {
    this.ingredientToIngredientCommand =
        new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
  }

  @Test
  public void findByRecipeIdAndIngredientId() {}

  @Test
  public void findByRecipeIdAndIngredientIdHappyPath() throws Exception {
    // given
    Recipe recipe = new Recipe();
    recipe.setId(1l);

    Ingredient ingredient = new Ingredient();
    ingredient.setId(1L);
    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId(2L);
    Ingredient ingredient3 = new Ingredient();
    ingredient3.setId(3L);

    recipe.addIngredient(ingredient);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
    Optional<Recipe> recipeOptional = Optional.of(recipe);
    // when
    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    // then
    IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1l, 3l);

    assertEquals(Long.valueOf(3l), ingredientCommand.getId());
    assertEquals(Long.valueOf(1l), ingredientCommand.getRecipeId());

    verify(recipeRepository, times(1)).findById(anyLong());
  }
}
