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

import com.sc.servicecompanies.application.services.WorkOrderService;
import com.sc.servicecompanies.domain.entities.WorkOrder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/work-orders")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping
    public List<WorkOrder> list() {
        return workOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<WorkOrder> workOrderOptional = workOrderService.findById(id);
        if (workOrderOptional.isPresent()) {
            return ResponseEntity.ok(workOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody WorkOrder workOrder, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(workOrderService.save(workOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody WorkOrder workOrder, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<WorkOrder> workOrderOptional = workOrderService.update(id, workOrder);
        if (workOrderOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(workOrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<WorkOrder> workOrderOptional = workOrderService.findById(id);
        if (!workOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<WorkOrder> workOrderDelete = workOrderService.delete(id);
        if (workOrderDelete.isPresent()) {
            return ResponseEntity.ok(workOrderDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(workOrderDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
