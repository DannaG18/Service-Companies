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

import com.sc.servicecompanies.application.services.ApprovalStatusService;
import com.sc.servicecompanies.domain.entities.ApprovalStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/approval-status")
public class ApprovalStatusController {
    @Autowired
    private ApprovalStatusService approvalStatusService;

    @GetMapping
    public List<ApprovalStatus> list() {
        return approvalStatusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<ApprovalStatus> approvalStatusOptional = approvalStatusService.findById(id);
        if (approvalStatusOptional.isPresent()) {
            return ResponseEntity.ok(approvalStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ApprovalStatus approvalStatus, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(approvalStatusService.save(approvalStatus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ApprovalStatus approvalStatus, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<ApprovalStatus> approvalStatusOptional = approvalStatusService.update(id, approvalStatus);
        if (approvalStatusOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(approvalStatusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ApprovalStatus> approvalStatusOptional = approvalStatusService.findById(id);
        if (!approvalStatusOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<ApprovalStatus> approvalStatusDelete = approvalStatusService.delete(id);
        if (approvalStatusDelete.isPresent()) {
            return ResponseEntity.ok(approvalStatusDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(approvalStatusDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}