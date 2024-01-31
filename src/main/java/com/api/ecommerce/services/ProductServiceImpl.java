package com.api.ecommerce.services;

import com.api.ecommerce.entities.Product;
import com.api.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

  @Autowired
  public ProductRepository productRepository;

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Optional<Product> getProductById(String id) {
    return productRepository.findById(id);
  }

  @Override
  public void update(Product product) {
    productRepository.save(product);
  }

  @Override
  public void delete(String id) {
    productRepository.deleteById(id);
  }
}
