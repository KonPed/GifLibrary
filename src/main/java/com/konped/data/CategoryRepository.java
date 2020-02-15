package com.konped.data;

import com.konped.model.Category;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryRepository {

  private static final List<Category> ALL_CATEGORIES = Arrays.asList(
          new Category(1, "Technology", "red"),
          new Category(2, "People", "Yellow"),
          new Category(3, "Destruction", "Green")
  );

  public List<Category> getAllCategories() {
    return ALL_CATEGORIES;
  }

  public Category findById(int id) {
    return ALL_CATEGORIES.stream().filter(category -> category.getId() == id).findAny().orElse(null);
  }
}
