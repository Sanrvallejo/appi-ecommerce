package com.api.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class OrderDetails {
  private String id;
  private String name;
  private double price;
  private double quantity;
  private double total;

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
