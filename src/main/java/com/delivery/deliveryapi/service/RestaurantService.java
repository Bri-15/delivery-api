package com.delivery.deliveryapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.deliveryapi.entity.Restaurant;
import com.delivery.deliveryapi.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public List<Restaurant> list() {
        return repo.findAll();
    }

    public Restaurant create(Restaurant r) {
        if (r.getActive() == null) r.setActive(true);
        return repo.save(r);
    }
}
