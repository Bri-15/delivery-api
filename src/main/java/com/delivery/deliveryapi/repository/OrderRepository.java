package com.delivery.deliveryapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.deliveryapi.entity.Order;
import com.delivery.deliveryapi.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByStatus(OrderStatus status);
}
