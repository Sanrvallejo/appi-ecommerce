package com.api.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Product {
  private String id;
  private String name;
  private String description;
  private String image;
  private double price;
  private  double quantity;

  public Product() {
  }

  public Product(String id, String name, String description, String image, double price, double quantity) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
    this.price = price;
    this.quantity = quantity;
  }
}
