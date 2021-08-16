package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.commands.RecipeCommand;
import com.nadarzy.springrecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();

  Recipe findById(long id);

  RecipeCommand findCommandById(Long id);

  RecipeCommand saveRecipeCommand(RecipeCommand command);

  void deleteById(Long idToDelete);
}
