package com.sc.servicecompanies.infrastructure.repositories.statusorder;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.StatusOrderService;
import com.sc.servicecompanies.domain.entities.StatusOrder;

public class StatusOrderImpl implements StatusOrderService{
    @Autowired
    private StatusOrderRepository statusOrderRepository;

    @Transactional
    @Override
    public Optional<StatusOrder> delete(Long id) {
        Optional<StatusOrder> statusOrderOp = statusOrderRepository.findById(id);
        statusOrderOp.ifPresent(statusOrderDb -> {
            statusOrderRepository.delete(statusOrderDb);
        });
        return statusOrderOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<StatusOrder> findAll() {
        return (List<StatusOrder>) statusOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<StatusOrder> findById(Long id) {
        return statusOrderRepository.findById(id);
    }

    @Transactional
    @Override
    public StatusOrder save(StatusOrder statusOrder) {
        return statusOrderRepository.save(statusOrder);
    }

    @Transactional
    @Override
    public Optional<StatusOrder> update(Long id, StatusOrder statusOrder) {
        Optional<StatusOrder> statusOrderOld = statusOrderRepository.findById(id);
        if (statusOrderOld.isPresent()) {
            StatusOrder statusOrderDb = statusOrderOld.orElseThrow();
            statusOrderDb.setName(statusOrder.getName());
            return Optional.of(statusOrderRepository.save(statusOrderDb));
        }
        return Optional.empty();
    }   
}
