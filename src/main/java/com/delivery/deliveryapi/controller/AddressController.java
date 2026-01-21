package com.delivery.deliveryapi.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryapi.entity.Address;
import com.delivery.deliveryapi.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public List<Address> my(Authentication auth) {
        return service.myAddresses(auth.getName());
    }

    @PostMapping("/me")
    public Address add(Authentication auth, @RequestBody Address a) {
        return service.add(auth.getName(), a);
    }
}
