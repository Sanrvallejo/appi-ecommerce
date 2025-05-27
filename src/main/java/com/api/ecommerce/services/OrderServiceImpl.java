package com.api.ecommerce.services;

import com.api.ecommerce.entities.Order;
import com.api.ecommerce.repository.IOrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

  @Autowired
  private IOrderRespository orderRespository;

  @Override
  public Order save(Order order) {
    return orderRespository.save(order);
  }

  @Override
  public List<Order> findAll() {
    return orderRespository.findAll();
  }

  public  String generateOrderNumber() {
    int number = 0;
    String numberText ="";

    List<Order> orders = orderRespository.findAll();
    List<Integer> numbers = new ArrayList<Integer>();

    orders.stream().forEach(or -> numbers.add(Integer.parseInt(or.getNumber())));

    if (orders.isEmpty()) {
      number = 1;
    }else {
      number = numbers.stream().max(Integer::compare).get();
      number++;
    }

    if(number<10) {
      numberText = "000000"+String.valueOf(number);
    } else if (number<100) {
      numberText = "00000"+String.valueOf(number);
    } else if (number<1000) {
      numberText = "0000"+String.valueOf(number);
    } else if (number<100000) {
      numberText = "000"+String.valueOf(number);
    }

    return numberText;
  }

}
