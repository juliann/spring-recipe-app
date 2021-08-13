package com.nadarzy.springrecipeapp.model;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

  Category category;

  @Before
  public void setUp() {
    category = new Category();
  }

  @org.junit.Test
  public void getId() {
    Long idValue = 4L;
    category.setId(4l);
    assertEquals(idValue, category.getId());
  }

  @org.junit.Test
  public void getDescription() {}

  @org.junit.Test
  public void getRecipes() {}
}
