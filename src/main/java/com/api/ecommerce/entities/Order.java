package com.api.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
@Entity()
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private Date createdAt;
  private Date receivedAt;
  private Date updatedAt;
  private String number;

  @ManyToOne()
  private User user;

  @OneToOne(mappedBy = "order")
  private OrderDetails orderDetails;

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
