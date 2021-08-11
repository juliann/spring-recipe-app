package com.nadarzy.springrecipeapp.controllers;

import com.nadarzy.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  public IndexController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  private final RecipeService recipeService;

  @RequestMapping({"/", "", "/index.html", "index"})
  public String getIndexPage(Model model) {
    model.addAttribute("recipes", recipeService.getRecipes());

    return "index";
  }
}
