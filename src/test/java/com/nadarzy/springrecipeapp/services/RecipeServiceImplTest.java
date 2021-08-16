package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.converters.RecipeCommandToRecipe;
import com.nadarzy.springrecipeapp.converters.RecipeToRecipeCommand;
import com.nadarzy.springrecipeapp.model.Recipe;
import com.nadarzy.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;
  @Mock RecipeRepository recipeRepository;

  @Mock RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock RecipeCommandToRecipe recipeCommandToRecipe;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    recipeService =
        new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  public void getRecipes() {
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipes = new HashSet<>();
    recipes.add(recipe);

    when(recipeRepository.findAll()).thenReturn(recipes);

    Set<Recipe> recipeSet = recipeService.getRecipes();
    assertEquals(1, recipeSet.size());
    verify(recipeRepository, times(1)).findAll();
  }

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void testDeleteById() {
    Long idToDelete = 2L;
    recipeService.deleteById(idToDelete);
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }
}
