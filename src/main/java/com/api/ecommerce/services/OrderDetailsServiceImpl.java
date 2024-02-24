package com.api.ecommerce.services;

import com.api.ecommerce.entities.OrderDetails;
import com.api.ecommerce.repository.IOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService{
  @Autowired
  private IOrderDetailsRepository orderDetailsRepository;

  @Override
  public OrderDetails save(OrderDetails orderDetails) {
    return orderDetailsRepository.save(orderDetails);
  }
}
