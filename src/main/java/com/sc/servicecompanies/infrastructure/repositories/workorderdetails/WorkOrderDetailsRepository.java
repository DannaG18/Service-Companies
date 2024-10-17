package com.sc.servicecompanies.infrastructure.repositories.workorderdetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.WorkOrderDetails;

@Repository
public interface WorkOrderDetailsRepository extends CrudRepository<WorkOrderDetails, Long>{
    
}
