package com.api.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.util.UUID;

@ToString
@Entity()
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Getter @Setter
  private String name;

  @Getter @Setter
  private String description;

  @Getter @Setter
  private String image;

  @Getter @Setter
  private double price;

  @Getter @Setter
  private double quantity;

  @ManyToOne()
  private User user;

  public Product() {
  }

  public Product(UUID id, String name, String description, String image, double price, double quantity) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
    this.price = price;
    this.quantity = quantity;
  }
}
