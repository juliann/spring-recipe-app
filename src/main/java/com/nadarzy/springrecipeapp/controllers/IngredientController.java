package com.nadarzy.springrecipeapp.controllers;

import com.nadarzy.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

  private final RecipeService recipeService;

  public IngredientController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {
    log.debug("getting ingr for recipe " + recipeId);
    model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(recipeId)));
    return "recipe/ingredient/list";
  }
}
