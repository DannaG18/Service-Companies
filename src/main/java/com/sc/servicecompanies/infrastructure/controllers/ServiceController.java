package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.ServicesService;
import com.sc.servicecompanies.domain.entities.Services;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServicesService serviceService;

    @GetMapping
    public List<Services> list() {
        return serviceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Services> serviceOptional = serviceService.findById(id);
        if(serviceOptional.isPresent()) {
            return ResponseEntity.ok(serviceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Services service, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.save(service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Services service, @PathVariable Long id, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Services> serviceOptional = serviceService.update(id, service);
        if(serviceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Services> serviceOptional = serviceService.delete(id);
        if(serviceOptional.isPresent()) {
            return ResponseEntity.ok(serviceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
