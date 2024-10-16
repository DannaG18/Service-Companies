package com.sc.servicecompanies.application.services;

import java.util.Optional;
import java.util.List;

import com.sc.servicecompanies.domain.entities.ServiceApproval;

public interface ServiceApprovalService {
    
    List<ServiceApproval> findAll();
    Optional<ServiceApproval> findById(Long id);
    ServiceApproval save(ServiceApproval ServiceApproval);
    Optional<ServiceApproval> update(Long id, ServiceApproval ServiceApproval);
    Optional<ServiceApproval> delete(Long id);

}
