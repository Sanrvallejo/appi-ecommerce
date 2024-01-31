package com.api.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@ToString
@Entity()
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "BINARY(16)")
  private String id;

  private String name;
  private String description;
  private String image;
  private double price;
  private double quantity;

  @ManyToOne()
  private User user;

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
