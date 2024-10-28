package com.sc.servicecompanies.infrastructure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.sc.servicecompanies.application.services.ServiceOrderService;
import com.sc.servicecompanies.domain.entities.ServiceOrder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/service-orders")
public class ServiceOrderController {
    @Autowired
    private ServiceOrderService serviceOrderService;

    @GetMapping
    public List<ServiceOrder> list() {
        return serviceOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderService.findById(id);
        if (serviceOrderOptional.isPresent()) {
            return ResponseEntity.ok(serviceOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ServiceOrder serviceOrder, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceOrderService.save(serviceOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ServiceOrder serviceOrder, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderService.update(id, serviceOrder);
        if (serviceOrderOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderService.findById(id);
        if (!serviceOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<ServiceOrder> serviceOrderDelete = serviceOrderService.delete(id);
        if (serviceOrderDelete.isPresent()) {
            return ResponseEntity.ok(serviceOrderDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceOrderDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
