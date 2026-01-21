package com.delivery.deliveryapi.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrderRequest {

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long addressId;

    @NotEmpty
    private List<Item> items;

    @Getter @Setter
    public static class Item {
        @NotNull
        private Long productId;

        @NotNull
        @Min(1)
        private Integer quantity;
    }
}
