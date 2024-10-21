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

import com.sc.servicecompanies.application.services.PhonePersonService;
import com.sc.servicecompanies.domain.entities.PhonePerson;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/phone-person")
@CrossOrigin(origins = "*")
public class PhonePersonController {
    @Autowired
    private PhonePersonService phonePersonService;

    @GetMapping
    public List<PhonePerson> list() {
        return phonePersonService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<PhonePerson> phonePersonOptional = phonePersonService.findById(id);
        if (phonePersonOptional.isPresent()) {
            return ResponseEntity.ok(phonePersonOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PhonePerson phonePerson, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(phonePersonService.save(phonePerson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody PhonePerson phonePerson, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<PhonePerson> phonePersonOptional = phonePersonService.update(id, phonePerson);
        if (phonePersonOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(phonePersonOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<PhonePerson> phonePersonOptional = phonePersonService.findById(id);
        if (!phonePersonOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<PhonePerson> phonePersonDelete = phonePersonService.delete(id);
        if (phonePersonDelete.isPresent()) {
            return ResponseEntity.ok(phonePersonDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(phonePersonDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
