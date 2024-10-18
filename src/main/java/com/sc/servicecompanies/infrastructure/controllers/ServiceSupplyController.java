package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.ServicesService;
import com.sc.servicecompanies.application.services.ServiceSupplyService;
import com.sc.servicecompanies.application.services.SupplyService;
import com.sc.servicecompanies.domain.entities.Services;
import com.sc.servicecompanies.domain.entities.ServiceSupply;
import com.sc.servicecompanies.domain.entities.Supply;
import com.sc.servicecompanies.domain.entities.fkclass.ServiceSupplyId;
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
@RequestMapping("/api/servicesupply")
public class ServiceSupplyController {

    @Autowired
    private ServiceSupplyService serviceSupplyService;
    @Autowired
    private ServicesService serviceService;
    @Autowired
    private SupplyService supplyService;

    @GetMapping("/list")
    public List<ServiceSupply> list() {
        return serviceSupplyService.findAll();
    }

    @GetMapping("/{serviceId}/{supplyId}")
    public ResponseEntity<?> view(@PathVariable Long serviceId, @PathVariable Long supplyId) {
        ServiceSupplyId id = new ServiceSupplyId(serviceId, supplyId);
        Optional<ServiceSupply> serviceSupplyOptional = serviceSupplyService.findById(id);
        if(serviceSupplyOptional.isPresent()) {
            return ResponseEntity.ok(serviceSupplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody ServiceSupply serviceSupply, BindingResult result) {
        Optional<Services> service = serviceService.findById(serviceSupply.getId().getServiceId());
        Optional<Supply> supply = supplyService.findById(serviceSupply.getId().getSupplyId());
        serviceSupply.setService(service.orElseThrow());
        serviceSupply.setSupply(supply.orElseThrow());

        if(result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceSupplyService.save(serviceSupply));
    }

    @PutMapping("/{serviceId}/{supplyId}")
    public ResponseEntity<?> update(@Valid @RequestBody ServiceSupply serviceSupply, @PathVariable Long serviceId, @PathVariable Long supplyId, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        ServiceSupplyId id = new ServiceSupplyId(serviceId, supplyId);
        Optional<ServiceSupply> serviceSupplyOptional = serviceSupplyService.update(id, serviceSupply);
        if(serviceSupplyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceSupplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{serviceId}/{supplyId}")
    public ResponseEntity<?> delete(@PathVariable Long serviceId, @PathVariable Long supplyId) {
        ServiceSupplyId id = new ServiceSupplyId(serviceId, supplyId);

        Optional<ServiceSupply> serviceSupplyOptional = serviceSupplyService.delete(id);
        if(serviceSupplyOptional.isPresent()) {
            return ResponseEntity.ok(serviceSupplyOptional.orElseThrow());
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
