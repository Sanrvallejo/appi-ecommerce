package com.api.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity()
@Table(name = "orders_details")
public class OrderDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "BINARY(16)")
  private String id;
  private String name;
  private double price;
  private double quantity;
  private double total;

  @OneToOne()
  private Order order;

  @ManyToOne()
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
