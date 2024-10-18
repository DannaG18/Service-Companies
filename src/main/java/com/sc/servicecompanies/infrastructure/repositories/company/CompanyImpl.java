package com.sc.servicecompanies.infrastructure.repositories.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.CompanyService;
import com.sc.servicecompanies.domain.entities.Company;

public class CompanyImpl implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    @Override
    public Optional<Company> delete(Long id) {
        Optional<Company> companyOp = companyRepository.findById(id);
        companyOp.ifPresent(companyDb -> {
            companyRepository.delete(companyDb);
        });
        return companyOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Company> findAll() {
        return (List<Company>) companyRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Transactional
    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    @Override
    public Optional<Company> update(Long id, Company company) {
        Optional<Company> companyOld = companyRepository.findById(id);
        if (companyOld.isPresent()) {
            Company companyDb = companyOld.orElseThrow();
            companyDb.setNameCompany(company.getNameCompany());
            companyDb.setCompanyType(company.getCompanyType());
            return Optional.of(companyRepository.save(companyDb));
        }
        return Optional.empty();
    }   
}
