package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.commands.IngredientCommand;

public interface IngredientService {
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
