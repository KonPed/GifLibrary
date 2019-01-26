package com.konped.controllers;

import com.konped.data.CategoryRepository;
import com.konped.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryController {

  @Autowired()
  private CategoryRepository categoryRepository;

  @RequestMapping(value = "/categories", method = RequestMethod.GET)
  public String categories(ModelMap modelMap) {
    List<Category> categories = categoryRepository.getAllCategories();
    modelMap.addAttribute(categories);
    return "categories";
  }
}
