package com.nadarzy.springrecipeapp.repositories;

import com.nadarzy.springrecipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {}
