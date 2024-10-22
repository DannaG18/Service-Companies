package com.sc.servicecompanies.infrastructure.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

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

import com.sc.servicecompanies.application.services.CityService;
import com.sc.servicecompanies.domain.entities.City;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/city")
@CrossOrigin(origins = "*")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> list() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isPresent()) {
            return ResponseEntity.ok(cityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody City city, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody City city, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<City> cityOptional = cityService.update(id, city);
        if (cityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<City> cityDelete = cityService.delete(id);
        if (cityDelete.isPresent()) {
            return ResponseEntity.ok(cityDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cityDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
