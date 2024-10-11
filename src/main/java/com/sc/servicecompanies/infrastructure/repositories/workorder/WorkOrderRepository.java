package com.sc.servicecompanies.infrastructure.repositories.workorder;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.WorkOrder;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long>{
    
}
