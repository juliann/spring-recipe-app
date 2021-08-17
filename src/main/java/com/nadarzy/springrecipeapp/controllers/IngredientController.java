package com.nadarzy.springrecipeapp.controllers;

import com.nadarzy.springrecipeapp.commands.IngredientCommand;
import com.nadarzy.springrecipeapp.services.IngredientService;
import com.nadarzy.springrecipeapp.services.RecipeService;
import com.nadarzy.springrecipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {
    log.debug("getting ingr for recipe " + recipeId);
    model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(recipeId)));
    return "recipe/ingredient/list";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
  public String showRecipeIngredient(
      @PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    model.addAttribute(
        "ingredient",
        ingredientService.findByRecipeIdAndIngredientId(
            Long.parseLong(recipeId), Long.parseLong(ingredientId)));
    return "recipe/ingredient/show";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
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
}
