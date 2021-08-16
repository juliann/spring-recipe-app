package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.commands.IngredientCommand;
import com.nadarzy.springrecipeapp.converters.IngredientToIngredientCommand;
import com.nadarzy.springrecipeapp.model.Recipe;
import com.nadarzy.springrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

  private final IngredientToIngredientCommand ingredient;
  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(
      IngredientToIngredientCommand ingredient, RecipeRepository recipeRepository) {
    this.ingredient = ingredient;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    if (!recipeOptional.isPresent()) {
      log.error("recipe id not found " + recipeId);
    }
    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> optionalIngredientCommand =
        recipe.getIngredients().stream()
            .filter(ingredient1 -> ingredient1.getId().equals(ingredientId))
            .map(ingredient1 -> ingredient.convert(ingredient1))
            .findFirst();
    if (!optionalIngredientCommand.isPresent()) {
      log.error("ingr id not found" + ingredientId);
    }
    return optionalIngredientCommand.get();
  }
}
