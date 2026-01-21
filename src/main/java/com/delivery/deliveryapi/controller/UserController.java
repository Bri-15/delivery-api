package com.delivery.deliveryapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryapi.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/me")
    public Object me(Authentication auth) {
        return repo.findByUsername(auth.getName()).orElseThrow();
    }
}
