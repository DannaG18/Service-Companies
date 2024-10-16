package com.sc.servicecompanies.infrastructure.repositories.serviceorder;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.ServiceOrder;

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder, Long>{
    
}
