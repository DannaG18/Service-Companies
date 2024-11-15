package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.auth.AuthenticationService;
import com.sc.servicecompanies.domain.entities.security.User;
import com.sc.servicecompanies.domain.entities.dto.auth.AuthenticationRequest;
import com.sc.servicecompanies.domain.entities.dto.auth.AuthenticationResponse;
import com.sc.servicecompanies.domain.entities.dto.auth.LogoutResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt) {
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok( new LogoutResponse("Successfully Logout"));
    }
    

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile() {
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
