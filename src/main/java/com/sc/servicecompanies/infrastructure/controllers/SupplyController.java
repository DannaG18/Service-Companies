package com.sc.servicecompanies.infrastructure.controllers;

import com.sc.servicecompanies.application.services.SupplyService;
import com.sc.servicecompanies.domain.entities.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    public List<Supply> list() {
        return supplyService.findAll();
    }
}
