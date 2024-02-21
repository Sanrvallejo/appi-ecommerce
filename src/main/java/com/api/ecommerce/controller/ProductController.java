package com.api.ecommerce.controller;

import com.api.ecommerce.entities.Product;
import com.api.ecommerce.entities.User;
import com.api.ecommerce.services.ProductServiceImpl;
import com.api.ecommerce.services.UploadFileService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

  private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductServiceImpl productService;

  @Autowired
  private UploadFileService uploadFileService;

  @GetMapping("")
  public String show(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    return "products/show";
  }

  @GetMapping("/create-product")
  public String create(){
    return "products/create";
  }

  @PostMapping("/save-products")
  public String saveProduct (Product product, MultipartFile file) throws IOException {
    LOGGER.info("Product: {}", product);
    User user = new User("550e8400-e29b-41d4-a716-446655440000", "", "", "", "", "", "", "");
    product.setUser(user);

    //set image
    if (product.getId() ==  null){ //when product is created (new product)
      String imageName = uploadFileService.saveImage(file);
      product.setImage(imageName);
    }else {
      if (file.isEmpty()){ //when editing a product without an image
        Product p = new Product();
        p = productService.getProductById(product.getId()).get();
        product.setImage(p.getImage());
      }else { // when editing a product with an image
        String imageName = uploadFileService.saveImage(file);
        product.setImage(imageName);
      }
    }

    productService.save(product);
    return "redirect:/products";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable String id, Model model){
    Optional<Product> optionalProduct = productService.getProductById(id);
    Product product = optionalProduct.get();

    LOGGER.info("Product: {}", product);
    model.addAttribute("product", product);
    return "products/edit";
  }

  @PostMapping("/update-product")
  public String updateProduct(Product product){
    productService.update(product);
    return "redirect:/products";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable String id){
    productService.delete(id);
    return "redirect:/products";
  }
}
