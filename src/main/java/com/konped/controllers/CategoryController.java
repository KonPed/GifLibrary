package com.konped.controllers;

import com.konped.FlashMessage;
import com.konped.data.CategoryRepository;
import com.konped.data.GifRepository;
import com.konped.model.Category;
import com.konped.model.Gif;
import com.konped.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

  @Autowired
  private CategoryService categoryService;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private GifRepository gifRepo;

  @GetMapping("/categories")
  public ModelAndView categories(ModelMap modelMap) {
    List<Category> categories = categoryService.findAll();
    modelMap.addAttribute("categories", categories);
    return new ModelAndView("categories", modelMap);
  }

  @GetMapping("/category/{id}")
  public String getCategoryById(@PathVariable("id") Long id, ModelMap modelMap) {
    Category category = categoryRepository.findById(id);
    modelMap.addAttribute(category);
    List<Gif> categorizedGifs = gifRepo.findCategoryById(id);
    modelMap.addAttribute("categorizedGifs", categorizedGifs);
    return "category";
  }

  @PostMapping("/categories")
  public ModelAndView addCategory(@Valid Category category,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      /* Include validation errors upon redirect. */
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
      /* Persist category obj upon redirect - not having the user to repeat information. */
      redirectAttributes.addFlashAttribute("category", category);
      /* Persist the flash message. */
      redirectAttributes.addFlashAttribute("flash", new FlashMessage("Failed to add category", FlashMessage.Status.FAILURE));
      return new ModelAndView("redirect:/categories/add");
    }
    categoryService.save(category);
    /* Persist the flash message. */
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category succesfully added", FlashMessage.Status.SUCCESS));
    return new ModelAndView("redirect:/categories");
  }

  @PostMapping("/categories/{categoryId}")
  public ModelAndView updateCaregory(@Valid Category category, BindingResult bindingResult, @RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      /* Include validation errors upon redirect. */
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
      /* Persist category obj upon redirect - not having the user to repeat information. */
      redirectAttributes.addFlashAttribute("category", category);
      /* Persist the flash message. */
      redirectAttributes.addFlashAttribute("flash", new FlashMessage("Failed to update category", FlashMessage.Status.FAILURE));
      return new ModelAndView("redirect:/categories/" + category.getId() + "/edit");
    }
    categoryService.save(category);
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category succesfully updated.", FlashMessage.Status.SUCCESS));
    return new ModelAndView("redirect:/categories");
  }

  @GetMapping("/categories/add")
  public String formNewCategory(ModelMap model) {
    if (!model.containsAttribute("category")) {
      model.addAttribute("category", new Category());
    }
    model.addAttribute("action", "/categories");
    model.addAttribute("heading", "New Category");
    model.addAttribute("submit", "Add");
    return "form";
  }

  @GetMapping("/categories/{categoryId}/edit")
  public String formEditCategory(@PathVariable("categoryId") Long categoryId, ModelMap modelMap) {
    if (!modelMap.containsAttribute("category")) {
      modelMap.addAttribute("category", categoryService.findCategoryByID(categoryId));
    }
    modelMap.addAttribute("action", String.format("/categories/%s", categoryId));
    modelMap.addAttribute("heading", "Edit Category");
    modelMap.addAttribute("submit", "Update");
    return "form";
  }

  @PostMapping("/categories/{categoryId}/delete")
  public ModelAndView deleteCategory(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
    Category category = categoryService.findCategoryByID(id);
//    if (category != null) {
//      redirectAttributes.addFlashAttribute("flash", new FlashMessage("Only empty categories can be deleted", FlashMessage.Status.FAILURE));
//      return new ModelAndView("redirect:/categories/" + id + "/edit");
//    }
    categoryService.delete(category);
    return new ModelAndView("redirect:/categories");
  }
}
