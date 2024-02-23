package com.api.ecommerce.repository;

import com.api.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRespository extends JpaRepository<Order, String> {
}
