package com.nadarzy.springrecipeapp.repositories;

import com.nadarzy.springrecipeapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

  public Optional<UnitOfMeasure> findByDescription(String description);
}
