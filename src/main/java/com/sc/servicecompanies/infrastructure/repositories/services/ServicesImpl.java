package com.sc.servicecompanies.infrastructure.repositories.services;

import com.sc.servicecompanies.application.services.ServicesService;
import com.sc.servicecompanies.domain.entities.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesImpl implements ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Transactional
    @Override
    public Optional<Services> delete(Long id) {
        Optional<Services> serviceOp = servicesRepository.findById(id);
        serviceOp.ifPresent(serviceDb -> {
            servicesRepository.delete(serviceDb);
        });
        return serviceOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Services> findAll() {
        return (List<Services>) servicesRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Services> findById(Long id) {
        return servicesRepository.findById(id);
    }

    @Transactional
    @Override
    public Services save(Services services) {
        return servicesRepository.save(services);
    }

    @Transactional
    @Override
    public Optional<Services> update(Long id, Services services) {
        Optional<Services> servicesOld = servicesRepository.findById(id);
        if (servicesOld.isPresent()) {
            Services servicesDb = servicesOld.orElseThrow();
            servicesDb.setName(services.getName());
            servicesDb.setExecutionTime(services.getExecutionTime());
            servicesDb.setRequireSupply(services.isRequireSupply());
            return Optional.of(servicesRepository.save(servicesDb));
        }
        return Optional.empty();
    } 
}
