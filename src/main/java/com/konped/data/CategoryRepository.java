package com.konped.data;

import com.konped.model.Category;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryRepository {

  private static final List<Category> ALL_CATEGORIES = Arrays.asList(
          new Category(1L, "Technology", "red"),
          new Category(2L, "People", "Yellow"),
          new Category(3L, "Destruction", "Green")
  );

  public List<Category> getAllCategories() {
    return ALL_CATEGORIES;
  }

  public Category findById(Long id) {
    return ALL_CATEGORIES.stream().filter(category -> category.getId().equals(id)).findAny().orElse(null);
  }
}
