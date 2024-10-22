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

import com.sc.servicecompanies.application.services.DocumentTypeService;
import com.sc.servicecompanies.domain.entities.DocumentType;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/document-type")
@CrossOrigin(origins = "*")
public class DocumentTypeController {
    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping
    public List<DocumentType> list() {
        return documentTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<DocumentType> documentTypeOptional = documentTypeService.findById(id);
        if (documentTypeOptional.isPresent()) {
            return ResponseEntity.ok(documentTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DocumentType documentType, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(documentTypeService.save(documentType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody DocumentType documentType, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<DocumentType> documentTypeOptional = documentTypeService.update(id, documentType);
        if (documentTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(documentTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<DocumentType> documentTypeOptional = documentTypeService.findById(id);
        if (!documentTypeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<DocumentType> documentTypeDelete = documentTypeService.delete(id);
        if (documentTypeDelete.isPresent()) {
            return ResponseEntity.ok(documentTypeDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(documentTypeDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
