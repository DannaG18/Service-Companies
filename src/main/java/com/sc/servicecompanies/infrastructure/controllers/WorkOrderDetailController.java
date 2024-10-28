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

import com.sc.servicecompanies.application.services.WorkOrderDetailsService;
import com.sc.servicecompanies.domain.entities.WorkOrderDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/work-order-details")
@CrossOrigin(origins = "*")
public class WorkOrderDetailController {
    @Autowired
    private WorkOrderDetailsService workOrderDetailsService;

    @GetMapping
    public List<WorkOrderDetails> list() {
        return workOrderDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<WorkOrderDetails> workOrderDetailsOptional = workOrderDetailsService.findById(id);
        if (workOrderDetailsOptional.isPresent()) {
            return ResponseEntity.ok(workOrderDetailsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody WorkOrderDetails workOrderDetails, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(workOrderDetailsService.save(workOrderDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody WorkOrderDetails workOrderDetails, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<WorkOrderDetails> workOrderDetailsOptional = workOrderDetailsService.update(id, workOrderDetails);
        if (workOrderDetailsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(workOrderDetailsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<WorkOrderDetails> workOrderDetailsOptional = workOrderDetailsService.findById(id);
        if (!workOrderDetailsOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<WorkOrderDetails> workOrderDetailsDelete = workOrderDetailsService.delete(id);
        if (workOrderDetailsDelete.isPresent()) {
            return ResponseEntity.ok(workOrderDetailsDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(workOrderDetailsDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
