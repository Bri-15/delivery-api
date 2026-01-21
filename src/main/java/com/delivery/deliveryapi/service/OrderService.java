package com.delivery.deliveryapi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.deliveryapi.dto.CreateOrderRequest;
import com.delivery.deliveryapi.entity.Order;
import com.delivery.deliveryapi.entity.OrderItem;
import com.delivery.deliveryapi.entity.OrderStatus;
import com.delivery.deliveryapi.repository.AddressRepository;
import com.delivery.deliveryapi.repository.OrderRepository;
import com.delivery.deliveryapi.repository.ProductRepository;
import com.delivery.deliveryapi.repository.RestaurantRepository;
import com.delivery.deliveryapi.repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final RestaurantRepository restaurantRepo;
    private final AddressRepository addressRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, UserRepository userRepo,
                        RestaurantRepository restaurantRepo, AddressRepository addressRepo,
                        ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.restaurantRepo = restaurantRepo;
        this.addressRepo = addressRepo;
        this.productRepo = productRepo;
    }

    public Order create(String username, CreateOrderRequest req) {
        var customer = userRepo.findByUsername(username).orElseThrow();
        var restaurant = restaurantRepo.findById(req.getRestaurantId()).orElseThrow();
        var address = addressRepo.findById(req.getAddressId()).orElseThrow();

        Order order = Order.builder()
                .customer(customer)
                .restaurant(restaurant)
                .address(address)
                .status(OrderStatus.CREADO)
                .total(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (var it : req.getItems()) {
            var product = productRepo.findById(it.getProductId()).orElseThrow();

            BigDecimal unit = product.getPrice();
            BigDecimal line = unit.multiply(BigDecimal.valueOf(it.getQuantity()));

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(it.getQuantity())
                    .unitPrice(unit)
                    .lineTotal(line)
                    .build();

            order.getItems().add(item);
            total = total.add(line);
        }

        order.setTotal(total);
        return orderRepo.save(order);
    }

    public List<Order> myOrders(String username) {
        var user = userRepo.findByUsername(username).orElseThrow();
        return orderRepo.findByCustomerId(user.getId());
    }

    // Para repartidor/admin: ver pedidos por estado
    public List<Order> listByStatus(OrderStatus status) {
        return orderRepo.findByStatus(status);
    }

    // Repartidor/admin: actualizar estado
    public Order updateStatus(Long id, OrderStatus status) {
        var order = orderRepo.findById(id).orElseThrow();
        order.setStatus(status);
        return orderRepo.save(order);
    }
}
