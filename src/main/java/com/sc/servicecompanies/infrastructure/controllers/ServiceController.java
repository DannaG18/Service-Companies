package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.ServiceService;
import com.sc.servicecompanies.domain.entities.Service;
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
    private ServiceService serviceService;

    @GetMapping("/list")
    public List<Service> list() {
        return serviceService.findAll();
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        if(serviceOptional.isPresent()) {
            return ResponseEntity.ok(serviceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Service service, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.save(service));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Service service, @PathVariable Long id, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Service> serviceOptional = serviceService.update(id, service);
        if(serviceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.delete(id);
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
