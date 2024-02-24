package com.api.ecommerce.repository;

import com.api.ecommerce.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, String> {
}
