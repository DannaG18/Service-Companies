package com.sc.servicecompanies.infrastructure.repositories.workorder;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sc.servicecompanies.application.services.WorkOrderService;
import com.sc.servicecompanies.domain.entities.WorkOrder;

public class WorkOrderImpl implements WorkOrderService{
    
        
    @Autowired
    private WorkOrderRepository repository;

    @Override
    public List<WorkOrder> findAll() {
        return (List<WorkOrder>) repository.findAll();
    }

    @Override
    public Optional<WorkOrder> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public WorkOrder save(WorkOrder WorkOrder) {
        return repository.save(WorkOrder);
    }

    @Override
    public Optional<WorkOrder> update(Long id, WorkOrder WorkOrder) {
        // Verificar si el objeto existe
        Optional<WorkOrder> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Actualizar el objeto existente
            WorkOrder.setId(id); // Asegurarse de que el ID sea el mismo
            return Optional.of(repository.save(WorkOrder));
        }
        return Optional.empty(); // Si no existe, retornar vacío
    }

    @Override
    public Optional<WorkOrder> delete(Long id) {
        Optional<WorkOrder> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Eliminar si existe
            repository.deleteById(id);
            return existingResponse; // Retornar el objeto eliminado
        }
        return Optional.empty();
     } // Si no existe, retornar vacío    }
}
