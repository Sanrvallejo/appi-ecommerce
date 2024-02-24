package com.api.ecommerce.services;

import com.api.ecommerce.entities.Order;

public interface IOrderService {
  Order save(Order order);
}
