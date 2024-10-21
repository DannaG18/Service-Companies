package com.sc.servicecompanies.infrastructure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.sc.servicecompanies.application.services.CompanyTypeService;
import com.sc.servicecompanies.domain.entities.CompanyType;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/company-type")
@CrossOrigin(origins = "*")
public class CompanyTypeController {
    @Autowired
    private CompanyTypeService companyTypeService;

    @GetMapping
    public List<CompanyType> list() {
        return companyTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<CompanyType> companyTypeOptional = companyTypeService.findById(id);
        if (companyTypeOptional.isPresent()) {
            return ResponseEntity.ok(companyTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CompanyType companyType, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(companyTypeService.save(companyType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CompanyType companyType, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<CompanyType> companyTypeOptional = companyTypeService.update(id, companyType);
        if (companyTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(companyTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<CompanyType> companyTypeOptional = companyTypeService.findById(id);
        if (!companyTypeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<CompanyType> companyTypeDelete = companyTypeService.delete(id);
        if (companyTypeDelete.isPresent()) {
            return ResponseEntity.ok(companyTypeDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(companyTypeDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
