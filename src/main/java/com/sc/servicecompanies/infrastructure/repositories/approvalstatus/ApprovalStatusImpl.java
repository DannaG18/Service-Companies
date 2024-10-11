package com.sc.servicecompanies.infrastructure.repositories.approvalstatus;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sc.servicecompanies.application.services.ApprovalStatusService;
import com.sc.servicecompanies.domain.entities.ApprovalStatus;

public class ApprovalStatusImpl implements ApprovalStatusService{
    
    @Autowired
    private ApprovalStatusRepository repository;

    @Override
    public List<ApprovalStatus> findAll() {
        return (List<ApprovalStatus>) repository.findAll();
    }

    @Override
    public Optional<ApprovalStatus> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ApprovalStatus save(ApprovalStatus ApprovalStatus) {
        return repository.save(ApprovalStatus);
    }

    @Override
    public Optional<ApprovalStatus> update(Long id, ApprovalStatus ApprovalStatus) {
        // Verificar si el objeto existe
        Optional<ApprovalStatus> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Actualizar el objeto existente
            ApprovalStatus.setId(id); // Asegurarse de que el ID sea el mismo
            return Optional.of(repository.save(ApprovalStatus));
        }
        return Optional.empty(); // Si no existe, retornar vacío
    }

    @Override
    public Optional<ApprovalStatus> delete(Long id) {
        Optional<ApprovalStatus> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Eliminar si existe
            repository.deleteById(id);
            return existingResponse; // Retornar el objeto eliminado
        }
        return Optional.empty();
     } // Si no existe, retornar vacío    }
}
