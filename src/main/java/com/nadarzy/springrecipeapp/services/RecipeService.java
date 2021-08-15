package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
  Set<Recipe> getRecipes();

  Recipe findById(long id);
}
