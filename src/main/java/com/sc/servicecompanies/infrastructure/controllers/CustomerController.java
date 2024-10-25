package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.auth.AuthenticationService;
import com.sc.servicecompanies.domain.entities.dto.RegisterUser;
import com.sc.servicecompanies.domain.entities.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<RegisterUser> registerOne(@RequestBody @Valid UserDto newUser) {
        RegisterUser registeredUser = authenticationService.registerOneCustomer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
