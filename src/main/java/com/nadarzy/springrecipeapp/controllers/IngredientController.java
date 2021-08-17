package com.nadarzy.springrecipeapp.controllers;

import com.nadarzy.springrecipeapp.commands.IngredientCommand;
import com.nadarzy.springrecipeapp.commands.RecipeCommand;
import com.nadarzy.springrecipeapp.commands.UnitOfMeasureCommand;
import com.nadarzy.springrecipeapp.services.IngredientService;
import com.nadarzy.springrecipeapp.services.RecipeService;
import com.nadarzy.springrecipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

  private final RecipeService recipeService;
  private final IngredientService ingredientService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(
      RecipeService recipeService,
      IngredientService ingredientService,
      UnitOfMeasureService unitOfMeasureService) {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @GetMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {
    log.debug("getting ingr for recipe " + recipeId);
    model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(recipeId)));
    return "recipe/ingredient/list";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
  public String showRecipeIngredient(
      @PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    model.addAttribute(
        "ingredient",
        ingredientService.findByRecipeIdAndIngredientId(
            Long.parseLong(recipeId), Long.parseLong(ingredientId)));
    return "recipe/ingredient/show";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
  public String updateRecipeIngredient(
      @PathVariable String recipeId, @PathVariable String id, Model model) {
    model.addAttribute(
        "ingredient",
        ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @PostMapping("recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    log.debug("saved receipe id:" + savedCommand.getRecipeId());
    log.debug("saved ingredient id:" + savedCommand.getId());

    return "redirect:/recipe/"
        + savedCommand.getRecipeId()
        + "/ingredient/"
        + savedCommand.getId()
        + "/show";
  }

  @GetMapping("recipe/{recipeId}/ingredient/new")
  public String newRecipe(@PathVariable String recipeId, Model model) {
    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
    // todo: exception if null
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setRecipeId(Long.valueOf(recipeId));
    model.addAttribute("ingredient", ingredientCommand);
    ingredientCommand.setUom(new UnitOfMeasureCommand());
    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
  public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
    log.debug("deleting ingredient id" + ingredientId);
    ingredientService.deleteIngredientById(Long.valueOf(recipeId), Long.valueOf(ingredientId));
    return "redirect:/recipe/" + recipeId + "/ingredients";
  }
}
