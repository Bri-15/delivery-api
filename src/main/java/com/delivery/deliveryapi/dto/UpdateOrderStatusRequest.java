package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.entity.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateOrderStatusRequest {
    @NotNull
    private OrderStatus status;
}
