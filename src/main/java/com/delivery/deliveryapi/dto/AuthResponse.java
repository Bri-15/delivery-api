package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long id;
    private String username;
    private Role role;
}
