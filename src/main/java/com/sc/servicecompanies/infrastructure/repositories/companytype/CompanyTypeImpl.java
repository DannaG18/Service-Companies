package com.sc.servicecompanies.infrastructure.repositories.companytype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.CompanyTypeService;
import com.sc.servicecompanies.domain.entities.CompanyType;

@Service
public class CompanyTypeImpl implements CompanyTypeService{
    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    @Transactional
    @Override
    public Optional<CompanyType> delete(Long id) {
        Optional<CompanyType> companyTypeOp = companyTypeRepository.findById(id);
        companyTypeOp.ifPresent(companyTypeDb -> {
            companyTypeRepository.delete(companyTypeDb);
        });
        return companyTypeOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompanyType> findAll() {
        return (List<CompanyType>) companyTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CompanyType> findById(Long id) {
        return companyTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public CompanyType save(CompanyType companyType) {
        return companyTypeRepository.save(companyType);
    }

    @Transactional
    @Override
    public Optional<CompanyType> update(Long id, CompanyType companyType) {
        Optional<CompanyType> companyTypeOld = companyTypeRepository.findById(id);
        if (companyTypeOld.isPresent()) {
            CompanyType companyTypeDb = companyTypeOld.orElseThrow();
            companyTypeDb.setDescription(companyType.getDescription());
            return Optional.of(companyTypeRepository.save(companyTypeDb));
        }
        return Optional.empty();
    }
}
