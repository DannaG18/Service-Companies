package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.PersonService;
import com.sc.servicecompanies.domain.entities.Person;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "*")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> list() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable String id) {
        Optional<Person> personOptional = personService.findById(id);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Person person, @PathVariable String id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Person> personOptional = personService.update(id, person);
        if (personOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Person> personOptional = personService.findById(id);
        if (!personOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Person> personDelete = personService.delete(id);
        if (personDelete.isPresent()) {
            return ResponseEntity.ok(personDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(personDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}

