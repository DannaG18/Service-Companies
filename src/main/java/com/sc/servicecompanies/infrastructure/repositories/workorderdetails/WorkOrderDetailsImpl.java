package com.sc.servicecompanies.infrastructure.repositories.workorderdetails;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.WorkOrderDetailsService;
import com.sc.servicecompanies.domain.entities.WorkOrderDetails;

@Service
public class WorkOrderDetailsImpl implements WorkOrderDetailsService{
    @Autowired
    private WorkOrderDetailsRepository workOrderDetailsRepository;

    @Transactional
    @Override
    public Optional<WorkOrderDetails> delete(Long id) {
        Optional<WorkOrderDetails> workOrderDetailsOp = workOrderDetailsRepository.findById(id);
        workOrderDetailsOp.ifPresent(workOrderDetailsDb -> {
            workOrderDetailsRepository.delete(workOrderDetailsDb);
        });
        return workOrderDetailsOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<WorkOrderDetails> findAll() {
        return (List<WorkOrderDetails>) workOrderDetailsRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<WorkOrderDetails> findById(Long id) {
        return workOrderDetailsRepository.findById(id);
    }

    @Transactional
    @Override
    public WorkOrderDetails save(WorkOrderDetails workOrderDetails) {
        return workOrderDetailsRepository.save(workOrderDetails);
    }

    @Transactional
    @Override
    public Optional<WorkOrderDetails> update(Long id, WorkOrderDetails workOrderDetails) {
        Optional<WorkOrderDetails> workOrderDetailsOld = workOrderDetailsRepository.findById(id);
        if (workOrderDetailsOld.isPresent()) {
            WorkOrderDetails workOrderDetailsDb = workOrderDetailsOld.orElseThrow();
            workOrderDetailsDb.setAssignedService(workOrderDetails.getAssignedService());
            workOrderDetailsDb.setDate(workOrderDetails.getDate());
            workOrderDetailsDb.setWorkOrder(workOrderDetails.getWorkOrder());
            workOrderDetailsDb.setStatusServiceOrder(workOrderDetails.getStatusServiceOrder());
            return Optional.of(workOrderDetailsRepository.save(workOrderDetailsDb));
        }
        return Optional.empty();
    } 
}
