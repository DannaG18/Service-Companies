package com.sc.servicecompanies.application.services;

import java.util.Optional;

import com.sc.servicecompanies.domain.entities.StatusOrder;

import java.util.List;

public interface StatusOrderService {
    
    List<StatusOrder> findAll();
    Optional<StatusOrder> findById(Long id);
    StatusOrder save(StatusOrder statusOrder);
    Optional<StatusOrder> update(Long id, StatusOrder statusOrder);
    Optional<StatusOrder> delete(Long id);

}
