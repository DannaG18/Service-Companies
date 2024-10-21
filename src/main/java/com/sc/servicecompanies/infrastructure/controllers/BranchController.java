package com.sc.servicecompanies.infrastructure.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.servicecompanies.application.services.BranchService;
import com.sc.servicecompanies.domain.entities.Branch;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/branch")
@CrossOrigin(origins = "*")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public List<Branch> list() {
        return branchService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Branch> branchOptional = branchService.findById(id);
        if (branchOptional.isPresent()) {
            return ResponseEntity.ok(branchOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Branch branch, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Branch branch, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Branch> branchOptional = branchService.update(id, branch);
        if (branchOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(branchOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Branch> branchOptional = branchService.findById(id);
        if (!branchOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Branch> branchDelete = branchService.delete(id);
        if (branchDelete.isPresent()) {
            return ResponseEntity.ok(branchDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(branchDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}