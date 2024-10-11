package com.sc.servicecompanies.infrastructure.repositories.serviceorderstatus;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.ServiceOrderStatus;

public interface ServiceOrderStatusRepository extends CrudRepository<ServiceOrderStatus, Long>{
    
}
