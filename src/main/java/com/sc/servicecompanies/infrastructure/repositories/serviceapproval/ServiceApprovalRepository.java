package com.sc.servicecompanies.infrastructure.repositories.serviceapproval;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.ServiceApproval;

public interface ServiceApprovalRepository extends CrudRepository<ServiceApproval, Long>{
    
}
