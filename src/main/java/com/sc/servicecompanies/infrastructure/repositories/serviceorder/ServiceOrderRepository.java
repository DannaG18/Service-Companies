package com.sc.servicecompanies.infrastructure.repositories.serviceorder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.ServiceOrder;

@Repository
public interface ServiceOrderRepository extends CrudRepository<ServiceOrder, Long>{
    
}
