package com.sc.servicecompanies.application.services;

import java.util.Optional;
import java.util.List;

import com.sc.servicecompanies.domain.entities.ApprovalStatus;

public interface ApprovalStatusService {
    
    List<ApprovalStatus> findAll();
    Optional<ApprovalStatus> findById(Long id);
    ApprovalStatus save(ApprovalStatus ApprovalStatus);
    Optional<ApprovalStatus> update(Long id, ApprovalStatus ApprovalStatus);
    Optional<ApprovalStatus> delete(Long id);
}
