package com.sc.servicecompanies.infrastructure.repositories.service;

import com.sc.servicecompanies.application.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Transactional
    @Override
    public List<com.sc.servicecompanies.domain.entities.Service> findAll() {
        return (List<com.sc.servicecompanies.domain.entities.Service>) serviceRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<com.sc.servicecompanies.domain.entities.Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Transactional
    @Override
    public com.sc.servicecompanies.domain.entities.Service save(com.sc.servicecompanies.domain.entities.Service service) {
        return serviceRepository.save(service);
    }

    @Transactional
    @Override
    public Optional<com.sc.servicecompanies.domain.entities.Service> update(Long id, com.sc.servicecompanies.domain.entities.Service service) {
        Optional<com.sc.servicecompanies.domain.entities.Service> serviceOld = serviceRepository.findById(id);
        if(serviceOld.isPresent()) {
            com.sc.servicecompanies.domain.entities.Service serviceDb = serviceOld.orElseThrow();

            serviceDb.setName(service.getName());
            serviceDb.setExecutionTime(service.getExecutionTime());
            serviceDb.setRequireSupply(service.isRequireSupply());

            return Optional.of(serviceRepository.save(serviceDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<com.sc.servicecompanies.domain.entities.Service> delete(Long id) {
        Optional<com.sc.servicecompanies.domain.entities.Service> service = serviceRepository.findById(id);
        service.ifPresent(serviceDb -> {
            serviceRepository.delete(serviceDb);
        });
        return service;
    }
}
