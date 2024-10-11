package com.sc.servicecompanies.infrastructure.repositories.servicesupply;

import com.sc.servicecompanies.application.services.ServiceSupplyService;
import com.sc.servicecompanies.domain.entities.ServiceSupply;
import com.sc.servicecompanies.domain.entities.fkclass.ServiceSupplyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSupplyServiceImpl implements ServiceSupplyService {

    @Autowired
    private ServiceSupplyRepository serviceSupplyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ServiceSupply> findAll() {
        return (List<ServiceSupply>) serviceSupplyRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<ServiceSupply> findById(ServiceSupplyId id) {
        return serviceSupplyRepository.findById(id);
    }

    @Transactional
    @Override
    public ServiceSupply save(ServiceSupply serviceSupply) {
        return serviceSupplyRepository.save(serviceSupply);
    }

    @Transactional
    @Override
    public Optional<ServiceSupply> update(ServiceSupplyId id, ServiceSupply serviceSupply) {
        Optional<ServiceSupply> serviceSupplyOld = serviceSupplyRepository.findById(id);
        if(serviceSupplyOld.isPresent()) {
            ServiceSupply serviceSupplyDb = serviceSupplyOld.orElseThrow();

            serviceSupplyDb.setService(serviceSupply.getService());
            serviceSupplyDb.setSupply(serviceSupply.getSupply());
            serviceSupplyDb.setUnitValue(serviceSupply.getUnitValue());
            serviceSupplyDb.setMaxStock(serviceSupply.getMaxStock());
            serviceSupplyDb.setMinStock(serviceSupply.getMinStock());
            return Optional.of(serviceSupplyRepository.save(serviceSupplyDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ServiceSupply> delete(ServiceSupplyId id) {
        Optional<ServiceSupply> serviceSupply = serviceSupplyRepository.findById(id);
        serviceSupply.ifPresent(serviceSupplyDb -> {
            serviceSupplyRepository.delete(serviceSupplyDb);
        });
        return serviceSupply;
    }
}
