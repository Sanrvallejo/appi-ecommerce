package com.api.ecommerce.services;

import com.api.ecommerce.entities.Order;
import com.api.ecommerce.repository.IOrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

  @Autowired
  private IOrderRespository orderRespository;

  @Override
  public Order save(Order order) {
    return orderRespository.save(order);
  }
}
