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

import com.sc.servicecompanies.application.services.EmailTypeService;
import com.sc.servicecompanies.domain.entities.EmailType;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/email-type")
public class EmailTypeController {
    @Autowired
    private EmailTypeService emailTypeService;

    @GetMapping
    public List<EmailType> list() {
        return emailTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<EmailType> emailTypeOptional = emailTypeService.findById(id);
        if (emailTypeOptional.isPresent()) {
            return ResponseEntity.ok(emailTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmailType emailType, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(emailTypeService.save(emailType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EmailType emailType, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<EmailType> emailTypeOptional = emailTypeService.update(id, emailType);
        if (emailTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(emailTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<EmailType> emailTypeOptional = emailTypeService.findById(id);
        if (!emailTypeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<EmailType> emailTypeDelete = emailTypeService.delete(id);
        if (emailTypeDelete.isPresent()) {
            return ResponseEntity.ok(emailTypeDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emailTypeDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
