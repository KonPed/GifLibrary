package com.konped.data;

import com.konped.model.Category;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryRepository {

  private static final List<Category> ALL_CATEGORIES = Arrays.asList(
          new Category(1, "Technology"),
          new Category(2, "People"),
          new Category(3, "Destruction")
  );

  public static List<Category> getAllCategories() {
    return ALL_CATEGORIES;
  }
}
