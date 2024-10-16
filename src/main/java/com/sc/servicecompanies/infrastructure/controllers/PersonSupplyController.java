package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.PersonSupplyService;
import com.sc.servicecompanies.domain.entities.PersonSupply;
import com.sc.servicecompanies.domain.entities.fkclass.PersonSupplyId;
import jakarta.validation.Valid;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/personsupply")
public class PersonSupplyController {

    @Autowired
    private PersonSupplyService personSupplyService;

    @GetMapping("/list")
    public List<PersonSupply> list() {
        return personSupplyService.findAll();
    }

    @GetMapping("/view/{serviceId}/{personId}/{supplyId}")
    public ResponseEntity<?> view (@PathVariable Long serviceId, @PathVariable Long supplyId, @PathVariable String personId) {
        PersonSupplyId id = new PersonSupplyId(serviceId, personId, supplyId);
        Optional<PersonSupply> personSupplyOptional = personSupplyService.findById(id);
        if(personSupplyOptional.isPresent()) {
            return ResponseEntity.ok(personSupplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PersonSupply personSupply, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personSupplyService.save(personSupply));
     }

     @PutMapping("/update/{serviceId}/{personId}/{serviceId}")
     public ResponseEntity<?> update(@Valid @RequestBody PersonSupply personSupply, @PathVariable Long serviceId, @PathVariable String personId, @PathVariable Long supplyId, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        PersonSupplyId id = new PersonSupplyId(serviceId, personId, supplyId);
        Optional<PersonSupply> personSupplyOptional = personSupplyService.update(id, personSupply);
        if(personSupplyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personSupplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
     }

     @DeleteMapping("/delete/{serviceId}/{personId}/{supplyId}")
     public ResponseEntity<?> delete(@PathVariable Long serviceId, @PathVariable String personId, @PathVariable Long supplyId) {
        PersonSupplyId id = new PersonSupplyId(serviceId, personId, supplyId);
        Optional<PersonSupply> personSupplyOptional = personSupplyService.delete(id);
        if(personSupplyOptional.isPresent()) {
            return ResponseEntity.ok(personSupplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
     }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
