package com.sc.servicecompanies.infrastructure.repositories.workorderdetails;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.WorkOrderDetails;

public interface WorkOrderDetailsRepository extends CrudRepository<WorkOrderDetails, Long>{
    
}
