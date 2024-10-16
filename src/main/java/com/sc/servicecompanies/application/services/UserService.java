package com.sc.servicecompanies.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sc.servicecompanies.domain.entities.User;
import com.sc.servicecompanies.domain.entities.dto.UserDto;

@Service
public interface UserService {
    User registrOneCustomer(UserDto newUser);
    Optional <User> findOneByUserName (String username);
}
