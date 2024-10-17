package com.sc.servicecompanies.infrastructure.repositories.serviceapproval;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.ServiceApprovalService;
import com.sc.servicecompanies.domain.entities.ServiceApproval;

@Service
public class ServiceApprovalImpl implements ServiceApprovalService{
    @Autowired
    private ServiceApprovalRepository serviceApprovalRepository;

    @Transactional
    @Override
    public Optional<ServiceApproval> delete(Long id) {
        Optional<ServiceApproval> serviceApprovalOp = serviceApprovalRepository.findById(id);
        serviceApprovalOp.ifPresent(serviceApprovalDb -> {
            serviceApprovalRepository.delete(serviceApprovalDb);
        });
        return serviceApprovalOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceApproval> findAll() {
        return (List<ServiceApproval>) serviceApprovalRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ServiceApproval> findById(Long id) {
        return serviceApprovalRepository.findById(id);
    }

    @Transactional
    @Override
    public ServiceApproval save(ServiceApproval serviceApproval) {
        return serviceApprovalRepository.save(serviceApproval);
    }

    @Transactional
    @Override
    public Optional<ServiceApproval> update(Long id, ServiceApproval serviceApproval) {
        Optional<ServiceApproval> serviceApprovalOld = serviceApprovalRepository.findById(id);
        if (serviceApprovalOld.isPresent()) {
            ServiceApproval serviceApprovalDb = serviceApprovalOld.orElseThrow();
            serviceApprovalDb.setWorkOrder(serviceApproval.getWorkOrder());
            serviceApprovalDb.setClient(serviceApproval.getClient());
            serviceApprovalDb.setService(serviceApproval.getService());
            serviceApprovalDb.setApprovalStatus(serviceApproval.getApprovalStatus());
            serviceApprovalDb.setIssueDescription(serviceApproval.getIssueDescription());
            serviceApprovalDb.setSolutionDescription(serviceApproval.getSolutionDescription());
            return Optional.of(serviceApprovalRepository.save(serviceApprovalDb));
        }
        return Optional.empty();
    } 
}
