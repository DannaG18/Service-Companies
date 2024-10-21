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

import com.sc.servicecompanies.application.services.EmailPersonService;
import com.sc.servicecompanies.domain.entities.EmailPerson;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/email-person")
public class EmailPersonController {
    @Autowired
    private EmailPersonService emailPersonService;

    @GetMapping
    public List<EmailPerson> list() {
        return emailPersonService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<EmailPerson> emailPersonOptional = emailPersonService.findById(id);
        if (emailPersonOptional.isPresent()) {
            return ResponseEntity.ok(emailPersonOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmailPerson emailPerson, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(emailPersonService.save(emailPerson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EmailPerson emailPerson, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<EmailPerson> emailPersonOptional = emailPersonService.update(id, emailPerson);
        if (emailPersonOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(emailPersonOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<EmailPerson> emailPersonOptional = emailPersonService.findById(id);
        if (!emailPersonOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<EmailPerson> emailPersonDelete = emailPersonService.delete(id);
        if (emailPersonDelete.isPresent()) {
            return ResponseEntity.ok(emailPersonDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emailPersonDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
