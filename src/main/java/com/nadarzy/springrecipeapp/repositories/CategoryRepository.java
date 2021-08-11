package com.nadarzy.springrecipeapp.repositories;

import com.nadarzy.springrecipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

  public Optional<Category> findByDescription(String description);
}
