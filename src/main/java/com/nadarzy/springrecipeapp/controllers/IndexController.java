package com.nadarzy.springrecipeapp.controllers;

import com.nadarzy.springrecipeapp.model.Recipe;
import com.nadarzy.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
@Controller
public class IndexController {

  public IndexController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  private final RecipeService recipeService;

  @RequestMapping({"/", "", "/index.html", "index"})
  public String getIndexPage(Model model) {
    log.debug("gettimg index page");
    Set<Recipe> recipes = recipeService.getRecipes();
    model.addAttribute("recipes", recipes);

    return "index";
  }
}
