package com.delivery.deliveryapi.controller;

import com.delivery.deliveryapi.entity.Product;
import com.delivery.deliveryapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> list(@RequestParam(required = false) Long restaurantId) {
        return service.list(restaurantId);
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return service.create(p);
    }
}
