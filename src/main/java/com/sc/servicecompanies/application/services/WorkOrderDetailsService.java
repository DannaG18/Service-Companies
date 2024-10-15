package com.sc.servicecompanies.application.services;

import java.util.Optional;

import com.sc.servicecompanies.domain.entities.WorkOrderDetails;

import java.util.List;

public interface WorkOrderDetailsService {
    
    List<WorkOrderDetails> findAll();
    Optional<WorkOrderDetails> findById(Long id);
    WorkOrderDetails save(WorkOrderDetails WorkOrderDetails);
    Optional<WorkOrderDetails> update(Long id, WorkOrderDetails WorkOrderDetails);
    Optional<WorkOrderDetails> delete(Long id);
}
