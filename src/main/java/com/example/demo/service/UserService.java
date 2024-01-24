package com.example.demo.service;


import com.example.demo.dto.RegistrationDto;
import com.example.demo.models.Users;

public interface UserService {
    void saveUser(RegistrationDto RegistrationDto);

    Users findByEmail(String email);

    Users findByUsername(String username);
}
