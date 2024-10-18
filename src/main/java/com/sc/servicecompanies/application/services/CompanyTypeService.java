package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.CompanyType;

public interface CompanyTypeService {
    List<CompanyType> findAll();
    Optional<CompanyType> findById(Long id);
    CompanyType save(CompanyType companyType);
    Optional<CompanyType> update(Long id, CompanyType companyType);
    Optional<CompanyType> delete(Long id);
}
