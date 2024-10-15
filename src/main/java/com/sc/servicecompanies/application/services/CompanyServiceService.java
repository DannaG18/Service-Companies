package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;
import com.sc.servicecompanies.domain.entities.CompanyService;

public interface CompanyServiceService {
    List<CompanyService> findAll();
    Optional<CompanyService> findById();
    CompanyService save(CompanyService companyService);
    Optional<CompanyService> update(Long id, CompanyService companyService);
    Optional<CompanyService> delete(Long id);
}
