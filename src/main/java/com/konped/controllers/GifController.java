package com.konped.controllers;

import com.konped.data.GifRepository;
import com.konped.model.Gif;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GifController {

  private GifRepository gifRepo = new GifRepository();

  @RequestMapping("/")
  public String listGifs() {
    return "home";
  }

  @RequestMapping(value = "/gif", method = RequestMethod.GET)
  public String gifDetails(@RequestParam("name") String name, ModelMap modelMap) {
    Gif gif = gifRepo.findByName(name);
    modelMap.addAttribute(gif);
    return "gif-details";
  }

}
