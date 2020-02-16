package com.konped.controllers;

import com.konped.FlashMessage;
import com.konped.data.GifRepository;
import com.konped.model.Gif;
import com.konped.service.GifService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class GifController {

  @Autowired
  private GifRepository gifRepo;

  @Autowired
  private GifService gifService;

  @GetMapping("/")
  public String listGifs(ModelMap modelMap) {
    List<Gif> gifList = gifService.findAllGifs();
    modelMap.addAttribute(gifList);
    return "home";
  }

  @GetMapping("/gif/{name}")
  public String gifDetails(@PathVariable("name") String gifName, ModelMap modelMap) {
    Gif gif = gifRepo.findByName(gifName);
    modelMap.addAttribute(gif);
    modelMap.addAttribute("localDate", gif.getDateUploaded());
    return "gif-details";
  }

  @GetMapping("/upload")
  public ModelAndView formNewGif(ModelMap modelMap) {
    if (!modelMap.containsAttribute("gif")) {
      modelMap.addAttribute("gif", new Gif());
    }
    return new ModelAndView("gifForm");
  }

  @PostMapping("/gifs")
  public String addGif(@RequestParam("gifImage") MultipartFile file, Gif gif, RedirectAttributes redirectAttributes) throws IOException {
    gifService.save(file, gif);
    /* Add a flashMessage upon success. */
    redirectAttributes.addFlashAttribute("flash", new FlashMessage("Image uploaded successfully.", FlashMessage.Status.SUCCESS));
    return "redirect:/";
  }

}
