package com.delivery.deliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.deliveryapi.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}
