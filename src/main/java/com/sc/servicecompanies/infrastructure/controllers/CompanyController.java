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

import com.sc.servicecompanies.application.services.CompanyService;
import com.sc.servicecompanies.domain.entities.Company;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> list() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (companyOptional.isPresent()) {
            return ResponseEntity.ok(companyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Company company, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Company company, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Company> companyOptional = companyService.update(id, company);
        if (companyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(companyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Company> companyDelete = companyService.delete(id);
        if (companyDelete.isPresent()) {
            return ResponseEntity.ok(companyDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(companyDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
