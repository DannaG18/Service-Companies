package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.ServicesService;
import com.sc.servicecompanies.domain.entities.Services;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {
    @Autowired
    private ServicesService servicesService;

    @GetMapping
    public List<Services> list() {
        return servicesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Services> servicesOptional = servicesService.findById(id);
        if (servicesOptional.isPresent()) {
            return ResponseEntity.ok(servicesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Services services, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicesService.save(services));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Services services, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Services> servicesOptional = servicesService.update(id, services);
        if (servicesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(servicesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Services> servicesOptional = servicesService.findById(id);
        if (!servicesOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Services> servicesDelete = servicesService.delete(id);
        if (servicesDelete.isPresent()) {
            return ResponseEntity.ok(servicesDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(servicesDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
