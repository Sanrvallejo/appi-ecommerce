package com.api.ecommerce.controller;

import com.api.ecommerce.entities.Order;
import com.api.ecommerce.entities.OrderDetails;
import com.api.ecommerce.entities.Product;
import com.api.ecommerce.entities.User;
import com.api.ecommerce.services.OrderDetailsServiceImpl;
import com.api.ecommerce.services.OrderServiceImpl;
import com.api.ecommerce.services.ProductServiceImpl;
import com.api.ecommerce.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  private ProductServiceImpl productService;

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private OrderServiceImpl orderService;

  @Autowired
  private OrderDetailsServiceImpl orderDetailsService;

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

    orderDetails.setName(product.getName());
    orderDetails.setPrice(product.getPrice());
    orderDetails.setQuantity(quantity);
    orderDetails.setTotal(product.getPrice()*quantity);
    orderDetails.setProduct(product);

    //validate that the same product is not added twice
    String idProduct = product.getId();
    boolean productFound = details.stream().anyMatch(p -> p.getProduct().getId().equals(idProduct));
    LOGGER.info("Product {}", idProduct);
    LOGGER.info("Product Found {}", productFound);

    if (!productFound) {
      details.add(orderDetails);
    }

    total = details.stream().mapToDouble(dt -> dt.getTotal()).sum();

    order.setTotal(total);

    model.addAttribute("cart", details);
    model.addAttribute("order", order);

    LOGGER.info("Product {}", optionalProduct);
    LOGGER.info("Quantity {}", quantity);
    return "user/cart";
  }

  //remove product from cart
  @GetMapping("/delete/cart/{id}")
  public String removeProduct(@PathVariable String id, Model model) {
    List<OrderDetails> newOrder = new ArrayList<OrderDetails>();
    double total = 0;

    //create a new list of products to recalculate values
    for (OrderDetails dOrder : details) {
      if (!dOrder.getProduct().getId().equals(id)) {
        newOrder.add(dOrder);
      }
    }

    details = newOrder;

    //recalculate values
    total = details.stream().mapToDouble(dt -> dt.getTotal()).sum();

    order.setTotal(total);

    model.addAttribute("cart", details);
    model.addAttribute("order", order);

    return "user/cart";
  }

  @GetMapping("/get-cart")
  public String getCart(Model model) {

    model.addAttribute("cart", details);
    model.addAttribute("order", order);
    return "user/cart";
  }

  @GetMapping("/order")
  public String getOrder(Model model) {

    User user = userService.getUserById("550e8400-e29b-41d4-a716-446655440000").get();
    model.addAttribute("cart", details);
    model.addAttribute("order", order);
    model.addAttribute("user", user);

    return "user/summary_order";
  }

  //save order and details order
  @GetMapping("/save-order")
  public String saveOrder() {
    Date orderDate = new Date();

    order.setCreatedAt(orderDate);
    order.setNumber(orderService.generateOrderNumber());

    //User
    User user = userService.getUserById("550e8400-e29b-41d4-a716-446655440000").get();
    order.setUser(user);

    orderService.save(order);

    //saveDetails
    for (OrderDetails dt: details){
      dt.setOrder(order);
      orderDetailsService.save(dt);
    }

    //clean orders
    order = new Order();
    details.clear();

    return "redirect:/";
  }

  @PostMapping("/search-product")
  public String searchProduct(@RequestParam String productName, Model model) {
    LOGGER.info(productName);
    List<Product> products =
            productService.getAllProducts().stream().filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(productName)).collect(Collectors.toList());
    model.addAttribute("products", products);
    return "user/home";
  }

}
