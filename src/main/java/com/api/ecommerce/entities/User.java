package com.api.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity()
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "BINARY(16)")
  private String id;
  private String name;
  private String lastname;
  private String email;
  private String phone;
  private String address;
  private String tipo;
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Product> products;

  @OneToMany(mappedBy = "user")
  private List<Order> orders;

  public User() {
  }

  public User(String id, String name, String lastname, String email, String phone, String address, String tipo,
              String password) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.tipo = tipo;
    this.password = password;
  }
}
