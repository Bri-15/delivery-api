package com.delivery.deliveryapi.auth.dto;

import com.delivery.deliveryapi.user.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {
    private String token;
    private Long id;
    private String username;
    private Role role;
}
