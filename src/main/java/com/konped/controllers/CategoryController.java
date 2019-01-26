package com.konped.controllers;

import com.konped.data.CategoryRepository;
import com.konped.data.GifRepository;
import com.konped.model.Category;
import com.konped.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryController {

  @Autowired()
  private CategoryRepository categoryRepository;
  @Autowired
  private GifRepository gifRepo;

  @RequestMapping(value = "/categories", method = RequestMethod.GET)
  public String categories(ModelMap modelMap) {
    List<Category> categories = categoryRepository.getAllCategories();
    modelMap.addAttribute("categories", categories);
    return "categories";
  }

  @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
  public String getCategoryById(@PathVariable("id") int id, ModelMap modelMap) {
    Category category = categoryRepository.findById(id);
    modelMap.addAttribute(category);
    List<Gif> categorizedGifs = gifRepo.findCategoryById(id);
    modelMap.addAttribute("categorizedGifs", categorizedGifs);
    return "category";
  }
}
