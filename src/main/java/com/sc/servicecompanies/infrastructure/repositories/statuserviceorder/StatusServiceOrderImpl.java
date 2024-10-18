package com.sc.servicecompanies.infrastructure.repositories.statuserviceorder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.StatusServiceOrderService;
import com.sc.servicecompanies.domain.entities.StatusServiceOrder;

@Service
public class StatusServiceOrderImpl implements StatusServiceOrderService{
    @Autowired
    private StatusServiceOrderRepository statusServiceOrderRepository;

    @Transactional
    @Override
    public Optional<StatusServiceOrder> delete(Long id) {
        Optional<StatusServiceOrder> statusServiceOrderOp = statusServiceOrderRepository.findById(id);
        statusServiceOrderOp.ifPresent(statusServiceOrderDb -> {
            statusServiceOrderRepository.delete(statusServiceOrderDb);
        });
        return statusServiceOrderOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<StatusServiceOrder> findAll() {
        return (List<StatusServiceOrder>) statusServiceOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<StatusServiceOrder> findById(Long id) {
        return statusServiceOrderRepository.findById(id);
    }

    @Transactional
    @Override
    public StatusServiceOrder save(StatusServiceOrder statusServiceOrder) {
        return statusServiceOrderRepository.save(statusServiceOrder);
    }

    @Transactional
    @Override
    public Optional<StatusServiceOrder> update(Long id, StatusServiceOrder statusServiceOrder) {
        Optional<StatusServiceOrder> statusServiceOrderOld = statusServiceOrderRepository.findById(id);
        if (statusServiceOrderOld.isPresent()) {
            StatusServiceOrder statusServiceOrderDb = statusServiceOrderOld.orElseThrow();
            statusServiceOrderDb.setNameStatusServiceOrder(statusServiceOrder.getNameStatusServiceOrder());
            return Optional.of(statusServiceOrderRepository.save(statusServiceOrderDb));
        }
        return Optional.empty();
    }   
}
