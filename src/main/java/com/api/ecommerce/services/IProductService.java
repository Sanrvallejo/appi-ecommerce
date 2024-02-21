package com.api.ecommerce.services;

import com.api.ecommerce.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
  public Product save(Product product);
  public Optional<Product> getProductById(String id);
  public void update(Product product);
  public void delete(String id);

  public List<Product> getAllProducts();
}
