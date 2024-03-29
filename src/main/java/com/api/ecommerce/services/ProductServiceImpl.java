package com.api.ecommerce.services;

import com.api.ecommerce.entities.Product;
import com.api.ecommerce.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

  @Autowired
  public IProductRepository productRepository;

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

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}
