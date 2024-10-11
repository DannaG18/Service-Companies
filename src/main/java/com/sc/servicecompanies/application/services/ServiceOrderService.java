package com.sc.servicecompanies.application.services;

import java.util.Optional;

import com.sc.servicecompanies.domain.entities.ServiceOrder;

import java.util.List;


public interface ServiceOrderService {
    
    List<ServiceOrder> findAll();
    Optional<ServiceOrder> findById(Long id);
    ServiceOrder save(ServiceOrder ServiceOrder);
    Optional<ServiceOrder> update(Long id, ServiceOrder ServiceOrder);
    Optional<ServiceOrder> delete(Long id);
}
