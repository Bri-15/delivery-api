package com.delivery.deliveryapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.deliveryapi.entity.Address;
import com.delivery.deliveryapi.repository.AddressRepository;
import com.delivery.deliveryapi.repository.UserRepository;

@Service
public class AddressService {

    private final AddressRepository repo;
    private final UserRepository userRepo;

    public AddressService(AddressRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public List<Address> myAddresses(String username) {
        var user = userRepo.findByUsername(username).orElseThrow();
        return repo.findByUserId(user.getId());
    }

    public Address add(String username, Address a) {
        var user = userRepo.findByUsername(username).orElseThrow();
        a.setUser(user);
        return repo.save(a);
    }
}
