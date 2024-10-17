package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.Branch;

public interface BranchService {
    List<Branch> findAll();
    Optional<Branch> findById(Long id);
    Branch save(Branch branch);
    Optional<Branch> update(Long id, Branch branch);
    Optional<Branch> delete(Long id);
}
