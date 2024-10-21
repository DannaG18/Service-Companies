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

import com.sc.servicecompanies.application.services.StatusServiceOrderService;
import com.sc.servicecompanies.domain.entities.StatusServiceOrder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/status-service-order")
public class StatusServiceOrderController {
    @Autowired
    private StatusServiceOrderService statusServiceOrderService;

    @GetMapping
    public List<StatusServiceOrder> list() {
        return statusServiceOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<StatusServiceOrder> statusServiceOrderOptional = statusServiceOrderService.findById(id);
        if (statusServiceOrderOptional.isPresent()) {
            return ResponseEntity.ok(statusServiceOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StatusServiceOrder statusServiceOrder, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(statusServiceOrderService.save(statusServiceOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody StatusServiceOrder statusServiceOrder, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<StatusServiceOrder> statusServiceOrderOptional = statusServiceOrderService.update(id, statusServiceOrder);
        if (statusServiceOrderOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(statusServiceOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<StatusServiceOrder> statusServiceOrderOptional = statusServiceOrderService.findById(id);
        if (!statusServiceOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<StatusServiceOrder> statusServiceOrderDelete = statusServiceOrderService.delete(id);
        if (statusServiceOrderDelete.isPresent()) {
            return ResponseEntity.ok(statusServiceOrderDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusServiceOrderDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
