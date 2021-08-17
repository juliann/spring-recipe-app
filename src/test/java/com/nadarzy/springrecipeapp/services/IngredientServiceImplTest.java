package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.commands.IngredientCommand;
import com.nadarzy.springrecipeapp.converters.IngredientCommandToIngredient;
import com.nadarzy.springrecipeapp.converters.IngredientToIngredientCommand;
import com.nadarzy.springrecipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.nadarzy.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.nadarzy.springrecipeapp.model.Ingredient;
import com.nadarzy.springrecipeapp.model.Recipe;
import com.nadarzy.springrecipeapp.repositories.RecipeRepository;
import com.nadarzy.springrecipeapp.repositories.UnitOfMeasureRepository;
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
  private final IngredientCommandToIngredient ingredientCommandToIngredient;

  @Mock UnitOfMeasureRepository unitOfMeasureRepository;

  public IngredientServiceImplTest() {
    this.ingredientCommandToIngredient =
        new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    this.ingredientToIngredientCommand =
        new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    ingredientService =
        new IngredientServiceImpl(
            ingredientToIngredientCommand,
            ingredientCommandToIngredient,
            recipeRepository,
            unitOfMeasureRepository);
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

  @Test
  public void testSaveRecipeCommand() throws Exception {
    // given
    IngredientCommand command = new IngredientCommand();
    command.setId(3L);
    command.setRecipeId(2L);

    Optional<Recipe> recipeOptional = Optional.of(new Recipe());

    Recipe savedRecipe = new Recipe();
    savedRecipe.addIngredient(new Ingredient());
    savedRecipe.getIngredients().iterator().next().setId(3L);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    when(recipeRepository.save(any())).thenReturn(savedRecipe);

    // when
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    // then
    assertEquals(Long.valueOf(3L), savedCommand.getId());
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, times(1)).save(any(Recipe.class));
  }
}
