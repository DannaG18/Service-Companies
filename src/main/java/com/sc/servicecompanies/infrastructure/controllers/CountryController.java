package com.sc.servicecompanies.infrastructure.controllers;

import java.util.Optional;
import java.util.List;
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

import com.sc.servicecompanies.application.services.CountryService;
import com.sc.servicecompanies.domain.entities.Country;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/country")
@CrossOrigin(origins = "*")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> list() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Country> countryOptional = countryService.findById(id);
        if (countryOptional.isPresent()) {
            return ResponseEntity.ok(countryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Country country, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Country country, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Country> countryOptional = countryService.update(id, country);
        if (countryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(countryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Country> countryDelete = countryService.delete(id);
        if (countryDelete.isPresent()) {
            return ResponseEntity.ok(countryDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(countryDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
