package com.sc.servicecompanies.infrastructure.repositories.serviceapproval;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.ServiceApproval;

@Repository
public interface ServiceApprovalRepository extends CrudRepository<ServiceApproval, Long>{
    
}
