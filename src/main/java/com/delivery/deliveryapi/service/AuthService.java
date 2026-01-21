package com.delivery.deliveryapi.service;

import java.time.Instant;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.delivery.deliveryapi.dto.AuthResponse;
import com.delivery.deliveryapi.dto.LoginRequest;
import com.delivery.deliveryapi.dto.RegisterRequest;
import com.delivery.deliveryapi.entity.User;
import com.delivery.deliveryapi.repository.UserRepository;
import com.delivery.deliveryapi.security.JwtService;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository repo,
            PasswordEncoder encoder,
            AuthenticationManager authManager,
            JwtService jwtService
    ) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req) {

        if (repo.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username ya existe");
        }

        User user = repo.save(User.builder()
                .username(req.getUsername().trim())
                .passwordHash(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .createdAt(Instant.now())
                .build());

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole().toString(),   // 👈 NO .name()
                user.getId()
        );

        return AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public AuthResponse login(LoginRequest req) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        User user = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole().toString(),
                user.getId()
        );

        return AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
