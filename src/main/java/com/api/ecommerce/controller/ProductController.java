package com.api.ecommerce.controller;

import com.api.ecommerce.entities.Product;
import com.api.ecommerce.entities.User;
import com.api.ecommerce.services.ProductServiceImpl;
import com.api.ecommerce.services.UploadFileService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
  public String saveProduct (Product product, @RequestParam("img") MultipartFile file) throws IOException {
    LOGGER.info("Product: {}", product);
    User user = new User("550e8400-e29b-41d4-a716-446655440000", "", "", "", "", "", "", "");
    product.setUser(user);

    //set image
    if (product.getId() ==  null && !file.isEmpty()){ //when product is created (new product)
      String imageName = uploadFileService.saveImage(file);
      product.setImage(imageName);
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
  public String updateProduct(Product product, @RequestParam("img") MultipartFile file) throws IOException{
    Product p = new Product();
    p = productService.getProductById(product.getId()).get();

    if (file.isEmpty()){ //when editing a product without an image
      product.setImage(p.getImage());

    }else { // when editing a product with an image
      //when the image is not the default image
      if (!p.getImage().equals("default.jpg")){
        uploadFileService.deleteImage(p.getImage());
      }

      String imageName = uploadFileService.saveImage(file);
      product.setImage(imageName);
    }

    product.setUser(p.getUser());
    productService.update(product);
    return "redirect:/products";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable String id){

    Product productToElminate = new Product();
    productToElminate = productService.getProductById(id).get();

    //when the image is not the default image
    if (!productToElminate.getImage().equals("default.jpg")){
      uploadFileService.deleteImage(productToElminate.getImage());
    }

    productService.delete(id);
    return "redirect:/products";
  }
}
