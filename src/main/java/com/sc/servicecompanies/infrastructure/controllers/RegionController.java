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

import com.sc.servicecompanies.application.services.RegionService;
import com.sc.servicecompanies.domain.entities.Region;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<Region> list() {
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Region> regionOptional = regionService.findById(id);
        if (regionOptional.isPresent()) {
            return ResponseEntity.ok(regionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Region region, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.save(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Region region, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Region> regionOptional = regionService.update(id, region);
        if (regionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(regionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Region> regionOptional = regionService.findById(id);
        if (!regionOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Region> regionDelete = regionService.delete(id);
        if (regionDelete.isPresent()) {
            return ResponseEntity.ok(regionDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(regionDelete.orElseThrow());
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
