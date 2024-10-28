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

import com.sc.servicecompanies.application.services.StatusOrderService;
import com.sc.servicecompanies.domain.entities.StatusOrder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/status-orders")
public class StatusOrderController {
    @Autowired
    private StatusOrderService statusOrderService;

    @GetMapping
    public List<StatusOrder> list() {
        return statusOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<StatusOrder> statusOrderOptional = statusOrderService.findById(id);
        if (statusOrderOptional.isPresent()) {
            return ResponseEntity.ok(statusOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StatusOrder statusOrder, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(statusOrderService.save(statusOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody StatusOrder statusOrder, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<StatusOrder> statusOrderOptional = statusOrderService.update(id, statusOrder);
        if (statusOrderOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(statusOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<StatusOrder> statusOrderOptional = statusOrderService.findById(id);
        if (!statusOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<StatusOrder> statusOrderDelete = statusOrderService.delete(id);
        if (statusOrderDelete.isPresent()) {
            return ResponseEntity.ok(statusOrderDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusOrderDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
