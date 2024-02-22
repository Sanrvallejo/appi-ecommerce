package com.api.ecommerce.controller;

import com.api.ecommerce.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  private ProductServiceImpl productService;

  @GetMapping("")
  public String home(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    return "user/home";
  }
}
