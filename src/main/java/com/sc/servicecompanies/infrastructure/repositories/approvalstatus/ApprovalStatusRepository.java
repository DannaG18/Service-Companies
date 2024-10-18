package com.sc.servicecompanies.infrastructure.repositories.approvalstatus;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.ApprovalStatus;

@Repository
public interface ApprovalStatusRepository extends CrudRepository<ApprovalStatus, Long> {

    
}
