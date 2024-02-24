package com.api.ecommerce.services;

import com.api.ecommerce.entities.OrderDetails;

public interface IOrderDetailsService {
  OrderDetails save(OrderDetails orderDetails);
}
