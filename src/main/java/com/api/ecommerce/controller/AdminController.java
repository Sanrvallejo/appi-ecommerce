package com.api.ecommerce.controller;

import com.api.ecommerce.entities.Product;
import com.api.ecommerce.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private ProductServiceImpl productService;

  @GetMapping("")
  public String home(Model model){
    List<Product> products = productService.getAllProducts();
    model.addAttribute("products", products);
    return "admin/home";
  }
}
