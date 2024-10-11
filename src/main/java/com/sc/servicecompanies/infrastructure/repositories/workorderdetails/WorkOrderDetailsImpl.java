package com.sc.servicecompanies.infrastructure.repositories.workorderdetails;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sc.servicecompanies.application.services.WorkOrderDetailsService;
import com.sc.servicecompanies.domain.entities.WorkOrderDetails;

public class WorkOrderDetailsImpl implements WorkOrderDetailsService{
    
        
    @Autowired
    private WorkOrderDetailsRepository repository;

    @Override
    public List<WorkOrderDetails> findAll() {
        return (List<WorkOrderDetails>) repository.findAll();
    }

    @Override
    public Optional<WorkOrderDetails> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public WorkOrderDetails save(WorkOrderDetails WorkOrderDetails) {
        return repository.save(WorkOrderDetails);
    }

    @Override
    public Optional<WorkOrderDetails> update(Long id, WorkOrderDetails WorkOrderDetails) {
        // Verificar si el objeto existe
        Optional<WorkOrderDetails> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Actualizar el objeto existente
            WorkOrderDetails.setId(id); // Asegurarse de que el ID sea el mismo
            return Optional.of(repository.save(WorkOrderDetails));
        }
        return Optional.empty(); // Si no existe, retornar vacío
    }

    @Override
    public Optional<WorkOrderDetails> delete(Long id) {
        Optional<WorkOrderDetails> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Eliminar si existe
            repository.deleteById(id);
            return existingResponse; // Retornar el objeto eliminado
        }
        return Optional.empty();
     } // Si no existe, retornar vacío    }
}
