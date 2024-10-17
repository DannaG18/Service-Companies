package com.sc.servicecompanies.infrastructure.repositories.workorder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.WorkOrder;

@Repository
public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long>{
    
}
