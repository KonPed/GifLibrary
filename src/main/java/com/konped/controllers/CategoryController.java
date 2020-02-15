package com.konped.controllers;

import com.konped.FlashMessage;
import com.konped.data.CategoryRepository;
import com.konped.data.GifRepository;
import com.konped.model.Category;
import com.konped.model.Gif;
import com.konped.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public String getCategoryById(@PathVariable("id") int id, ModelMap modelMap) {
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
      /* Persist category obj upon redirect - not having the user to retpe information. */
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

  @GetMapping("/categories/add")
  public String formNewCategory(Model model, RedirectAttributes redirectAttributes) {
    if (!model.containsAttribute("category")) {
      model.addAttribute("category", new Category());
    }
    return "form";
  }
}
