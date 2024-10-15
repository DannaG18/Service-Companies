package com.sc.servicecompanies.infrastructure.repositories.approvalstatus;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.ApprovalStatus;

public interface ApprovalStatusRepository extends CrudRepository<ApprovalStatus, Long> {

    
}
