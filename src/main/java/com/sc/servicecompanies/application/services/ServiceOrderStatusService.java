package com.sc.servicecompanies.application.services;

import java.util.Optional;
import java.util.List;

import com.sc.servicecompanies.domain.entities.ServiceOrderStatus;

public interface ServiceOrderStatusService {
    
    List<ServiceOrderStatus> findAll();
    Optional<ServiceOrderStatus> findById(Long id);
    ServiceOrderStatus save(ServiceOrderStatus ServiceOrderStatus);
    Optional<ServiceOrderStatus> update(Long id, ServiceOrderStatus ServiceOrderStatus);
    Optional<ServiceOrderStatus> delete(Long id);

}
