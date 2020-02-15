package com.konped.controllers;

import com.konped.data.GifRepository;
import com.konped.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GifController {

  @Autowired
  private GifRepository gifRepo;

  @GetMapping("/")
  public String listGifs(ModelMap modelMap) {
    List<Gif> gifList = gifRepo.getAllGifs();
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

}
