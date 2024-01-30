package com.api.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity()
@Table(name = "orders_details")
public class OrderDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String name;
  private double price;
  private double quantity;
  private double total;

  @OneToOne()
  private Order order;

  @OneToOne()
  private Product product;

  public OrderDetails() {
  }

  public OrderDetails(String id, String name, double price, double quantity, double total) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.total = total;
  }
}
