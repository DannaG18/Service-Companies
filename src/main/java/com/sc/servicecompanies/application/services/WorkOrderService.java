package com.sc.servicecompanies.application.services;

import java.util.Optional;
import java.util.List;

import com.sc.servicecompanies.domain.entities.WorkOrder;

public interface WorkOrderService {
    
    List<WorkOrder> findAll();
    Optional<WorkOrder> findById(Long id);
    WorkOrder save(WorkOrder WorkOrder);
    Optional<WorkOrder> update(Long id, WorkOrder WorkOrder);
    Optional<WorkOrder> delete(Long id);

}
