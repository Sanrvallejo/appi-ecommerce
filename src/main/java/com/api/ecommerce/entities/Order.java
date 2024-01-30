package com.api.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class Order {
  private String id;
  private Date createdAt;
  private Date receivedAt;
  private Date updatedAt;
  private String number;

  public Order() {
  }

  public Order(String id, Date createdAt, Date receivedAt, Date updatedAt, String number) {
    this.id = id;
    this.createdAt = createdAt;
    this.receivedAt = receivedAt;
    this.updatedAt = updatedAt;
    this.number = number;
  }
}
