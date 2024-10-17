package com.sc.servicecompanies.infrastructure.repositories.workorder;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.WorkOrderService;
import com.sc.servicecompanies.domain.entities.WorkOrder;

@Service
public class WorkOrderImpl implements WorkOrderService{
    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Transactional
    @Override
    public Optional<WorkOrder> delete(Long id) {
        Optional<WorkOrder> workOrderOp = workOrderRepository.findById(id);
        workOrderOp.ifPresent(workOrderDb -> {
            workOrderRepository.delete(workOrderDb);
        });
        return workOrderOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<WorkOrder> findAll() {
        return (List<WorkOrder>) workOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<WorkOrder> findById(Long id) {
        return workOrderRepository.findById(id);
    }

    @Transactional
    @Override
    public WorkOrder save(WorkOrder workOrder) {
        return workOrderRepository.save(workOrder);
    }

    @Transactional
    @Override
    public Optional<WorkOrder> update(Long id, WorkOrder workOrder) {
        Optional<WorkOrder> workOrderOld = workOrderRepository.findById(id);
        if (workOrderOld.isPresent()) {
            WorkOrder workOrderDb = workOrderOld.orElseThrow();
            workOrderDb.setAssignmentDate(workOrder.getAssignmentDate());
            workOrderDb.setAssignmentHour(workOrder.getAssignmentHour());
            workOrderDb.setEmployee(workOrder.getEmployee());
            workOrderDb.setServiceOrder(workOrder.getServiceOrder());
            return Optional.of(workOrderRepository.save(workOrderDb));
        }
        return Optional.empty();
    } 
}
