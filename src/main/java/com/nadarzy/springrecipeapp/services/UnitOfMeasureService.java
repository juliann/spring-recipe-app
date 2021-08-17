package com.nadarzy.springrecipeapp.services;

import com.nadarzy.springrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
  Set<UnitOfMeasureCommand> listAllUoms();
}
