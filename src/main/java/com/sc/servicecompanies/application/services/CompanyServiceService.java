package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;
import com.sc.servicecompanies.domain.entities.CompanyService;
import com.sc.servicecompanies.domain.entities.fkclass.CompanyServiceId;

public interface CompanyServiceService {
    List<CompanyService> findAll();
    Optional<CompanyService> findById(CompanyServiceId id);
    CompanyService save(CompanyService companyService);
    Optional<CompanyService> update(CompanyServiceId id, CompanyService companyService);
    Optional<CompanyService> delete(CompanyServiceId id);
}
