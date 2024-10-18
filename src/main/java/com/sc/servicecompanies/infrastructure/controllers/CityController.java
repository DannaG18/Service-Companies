package com.sc.servicecompanies.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sc.servicecompanies.application.services.CityService;
import com.sc.servicecompanies.domain.entities.City;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> list() {
        return cityService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isPresent()){
            return ResponseEntity.ok(cityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody City city) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody City city, @PathVariable Long id) {
        Optional<City> cityOptional = cityService.update(id, city);
        if(cityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(cityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Optional<City> cityDelete = cityService.delete(id);
        if (cityDelete.isPresent()){
            return ResponseEntity.ok(cityDelete.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cityDelete.orElseThrow());
    }
}
