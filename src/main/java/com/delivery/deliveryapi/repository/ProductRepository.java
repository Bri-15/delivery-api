package com.delivery.deliveryapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.deliveryapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByRestaurantId(Long restaurantId);
}
