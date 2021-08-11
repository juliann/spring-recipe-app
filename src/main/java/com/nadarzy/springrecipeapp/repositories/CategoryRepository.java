package com.nadarzy.springrecipeapp.repositories;

import com.nadarzy.springrecipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {}
