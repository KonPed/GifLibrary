package com.konped.dao;

import com.konped.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
    Category findCategoryByID(Long id);
    void save(Category category);
    void delete(Category category);
}
