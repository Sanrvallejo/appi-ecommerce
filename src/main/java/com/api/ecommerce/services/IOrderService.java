package com.api.ecommerce.services;

import com.api.ecommerce.entities.Order;

import java.util.List;

public interface IOrderService {
  Order save(Order order);
  List<Order> findAll();
}
