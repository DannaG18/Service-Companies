package com.sc.servicecompanies.infrastructure.repositories.companyservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.CompanyServiceService;
import com.sc.servicecompanies.domain.entities.CompanyService;
import com.sc.servicecompanies.domain.entities.fkclass.CompanyServiceId;

public class CompanyServiceImpl implements CompanyServiceService {
    @Autowired
    private CompanyServiceRepository companyServiceRepository;

    @Transactional
    @Override
    public Optional<CompanyService> delete(CompanyServiceId id) {
        Optional<CompanyService> companyServiceOp = companyServiceRepository.findById(id);
        companyServiceOp.ifPresent(companyServiceDb -> {
            companyServiceRepository.delete(companyServiceDb);
        });
        return companyServiceOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompanyService> findAll() {
        return (List<CompanyService>) companyServiceRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CompanyService> findById(CompanyServiceId id) {
        return companyServiceRepository.findById(id);
    }

    @Transactional
    @Override
    public CompanyService save(CompanyService companyService) {
        return companyServiceRepository.save(companyService);
    }

    @Transactional
    @Override
    public Optional<CompanyService> update(CompanyServiceId id, CompanyService companyService) {
        Optional<CompanyService> companyServiceOld = companyServiceRepository.findById(id);
        if (companyServiceOld.isPresent()) {
            CompanyService companyServiceDb = companyServiceOld.orElseThrow();
            companyServiceDb.setService(companyService.getService());
            companyServiceDb.setBranch(companyService.getBranch());
            companyServiceDb.setServiceValue(companyService.getServiceValue());
            return Optional.of(companyServiceRepository.save(companyServiceDb));
        }
        return Optional.empty();
    }   
}
