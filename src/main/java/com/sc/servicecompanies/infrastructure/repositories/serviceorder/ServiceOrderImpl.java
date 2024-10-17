package com.sc.servicecompanies.infrastructure.repositories.serviceorder;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.ServiceOrderService;
import com.sc.servicecompanies.domain.entities.ServiceOrder;

@Service
public class ServiceOrderImpl implements ServiceOrderService{
    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Transactional
    @Override
    public Optional<ServiceOrder> delete(Long id) {
        Optional<ServiceOrder> serviceOrderOp = serviceOrderRepository.findById(id);
        serviceOrderOp.ifPresent(serviceOrderDb -> {
            serviceOrderRepository.delete(serviceOrderDb);
        });
        return serviceOrderOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceOrder> findAll() {
        return (List<ServiceOrder>) serviceOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ServiceOrder> findById(Long id) {
        return serviceOrderRepository.findById(id);
    }

    @Transactional
    @Override
    public ServiceOrder save(ServiceOrder serviceOrder) {
        return serviceOrderRepository.save(serviceOrder);
    }

    @Transactional
    @Override
    public Optional<ServiceOrder> update(Long id, ServiceOrder serviceOrder) {
        Optional<ServiceOrder> serviceOrderOld = serviceOrderRepository.findById(id);
        if (serviceOrderOld.isPresent()) {
            ServiceOrder serviceOrderDb = serviceOrderOld.orElseThrow();
            serviceOrderDb.setOrderDate(serviceOrder.getOrderDate());
            serviceOrderDb.setClient(serviceOrder.getClient());
            serviceOrderDb.setEmployee(serviceOrder.getEmployee());
            serviceOrderDb.setStatusOrder(serviceOrder.getStatusOrder());
            return Optional.of(serviceOrderRepository.save(serviceOrderDb));
        }
        return Optional.empty();
    } 
}
