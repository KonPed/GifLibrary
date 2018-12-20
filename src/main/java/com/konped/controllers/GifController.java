package com.konped.controllers;

import com.konped.model.Gif;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
public class GifController {

  @RequestMapping("/")
  public String listGifs() {
    return "home";
  }

  @RequestMapping(value = "/gif", method = RequestMethod.GET)
  public String gifDetails(ModelMap modelMap) {
    Gif gif = new Gif("compiler-bot", LocalDate.of(1987, 4, 28),
            "konstantinos", true );
    modelMap.addAttribute(gif);
    return "gif-details";
  }

}
