package com.konped.service;

import com.konped.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findCategoryByID(Long id);
    void save(Category category);
    void delete(Category category);
}
