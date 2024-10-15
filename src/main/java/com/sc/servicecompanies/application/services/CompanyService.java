package com.sc.servicecompanies.application.services;
import java.util.List;

import java.util.Optional;

import com.sc.servicecompanies.domain.entities.Company;

public interface CompanyService {
    List<Company> findAll();
    Optional<Company> findById(Long id);
    Company save(Company company);
    Optional<Company> update(Long id, Company company);
    Optional<Company> delete(Long id);
}
