package com.api.ecommerce.controller;

import com.api.ecommerce.entities.Order;
import com.api.ecommerce.entities.OrderDetails;
import com.api.ecommerce.entities.Product;
import com.api.ecommerce.services.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  private ProductServiceImpl productService;

  List<OrderDetails> details = new ArrayList<OrderDetails>();
  Order order = new Order();

  private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  @GetMapping("")
  public String home(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    return "user/home";
  }

  @GetMapping("product-details/{id}")
  public String productDetails(@PathVariable String id, Model model) { //product_home view
    LOGGER.info("Product ID {}", id);
    Product product = new Product();
    Optional<Product> productOptional = productService.getProductById(id);

    product = productOptional.get();

    model.addAttribute("product", product);
    return "user/product_home";
  }

  @PostMapping("/cart")
  public String addCart(Model model, @RequestParam String id, @RequestParam double quantity) {
    Product product = new Product();
    OrderDetails orderDetails = new OrderDetails();
    double total = 0;

    Optional<Product> optionalProduct = productService.getProductById(id);

    product = optionalProduct.get();
    orderDetails.setQuantity(quantity);
    orderDetails.setPrice(product.getPrice());
    orderDetails.setName(product.getName());
    orderDetails.setTotal(product.getPrice()*quantity);
    orderDetails.setProduct(product);

    details.add(orderDetails);

    total = details.stream().mapToDouble(dt -> dt.getTotal()).sum();

    order.setTotal(total);

    model.addAttribute("cart", details);
    model.addAttribute("order", order);

    LOGGER.info("Product {}", optionalProduct);
    LOGGER.info("Quantity {}", quantity);
    return "user/cart";
  }
}
