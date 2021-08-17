package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.commands.IngredientCommand;

public interface IngredientService {
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

  IngredientCommand saveIngredientCommand(IngredientCommand command);

  void deleteIngredientById(Long recipeId, Long ingredientId);
}
