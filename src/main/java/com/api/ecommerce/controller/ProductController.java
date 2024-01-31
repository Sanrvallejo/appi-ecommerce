package com.api.ecommerce.controller;

import com.api.ecommerce.entities.Product;
import com.api.ecommerce.entities.User;
import com.api.ecommerce.services.ProductServiceImpl;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

  private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductServiceImpl productService;

  @GetMapping("")
  public String show(){
    return "products/show";
  }

  @GetMapping("/create-product")
  public String create(){
    return "products/create";
  }

  @PostMapping("/save-products")
  public String saveProduct (Product product){
    LOGGER.info("Product: {}", product);
    User user = new User("550e8400-e29b-41d4-a716-446655440000", "", "", "", "", "", "", "");
    product.setUser(user);
    productService.save(product);
    return "redirect:/products";
  }
}
