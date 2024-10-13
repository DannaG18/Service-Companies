package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.SupplyService;
import com.sc.servicecompanies.domain.entities.Supply;
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
@RequestMapping("/api/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @GetMapping("/list")
    public List<Supply> list() {
        return supplyService.findAll();
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> view(@PathVariable Long id, BindingResult result) {
        Optional<Supply> supplyOptional = supplyService.findById(id);
        if(supplyOptional.isPresent()) {
            return ResponseEntity.ok(supplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Supply supply, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyService.save(supply));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Supply supply, @PathVariable Long id, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Supply> supplyOptional = supplyService.update(id, supply);
        if(supplyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(supplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Supply> supplyOptional = supplyService.delete(id);
        if(supplyOptional.isPresent()) {
            return ResponseEntity.ok(supplyOptional.orElseThrow());
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
