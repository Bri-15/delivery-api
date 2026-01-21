package com.delivery.deliveryapi.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryapi.dto.CreateOrderRequest;
import com.delivery.deliveryapi.dto.UpdateOrderStatusRequest;
import com.delivery.deliveryapi.entity.Order;
import com.delivery.deliveryapi.entity.OrderStatus;
import com.delivery.deliveryapi.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // Cliente crea pedido
    @PostMapping
    public Order create(Authentication auth, @Valid @RequestBody CreateOrderRequest req) {
        return service.create(auth.getName(), req);
    }

    // Cliente ve sus pedidos
    @GetMapping("/me")
    public List<Order> my(Authentication auth) {
        return service.myOrders(auth.getName());
    }

    // Repartidor/Admin: listar por estado
    @GetMapping
    public List<Order> listByStatus(@RequestParam OrderStatus status) {
        return service.listByStatus(status);
    }

    // Repartidor/Admin: actualizar estado
    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateOrderStatusRequest req) {
        return service.updateStatus(id, req.getStatus());
    }
}
