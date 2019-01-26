package com.konped.controllers;

import com.konped.data.GifRepository;
import com.konped.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GifController {

  @Autowired
  private GifRepository gifRepo;

  @RequestMapping("/")
  public String listGifs() {
    return "home";
  }

  @RequestMapping(value = "/gif/{name}", method = RequestMethod.GET)
  public String gifDetails(@PathVariable("name") String gifName, ModelMap modelMap) {
    Gif gif = gifRepo.findByName(gifName);
    modelMap.addAttribute(gif);
    modelMap.addAttribute("localDate", gif.getDateUploaded());
    return "gif-details";
  }

}
