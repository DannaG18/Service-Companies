package com.sc.servicecompanies.infrastructure.repositories.serviceapproval;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sc.servicecompanies.application.services.ServiceApprovalService;
import com.sc.servicecompanies.domain.entities.ServiceApproval;

public class ServiceApprovalImpl implements ServiceApprovalService{
    
    @Autowired
    private ServiceApprovalRepository repository;

    @Override
    public List<ServiceApproval> findAll() {
        return (List<ServiceApproval>) repository.findAll();
    }

    @Override
    public Optional<ServiceApproval> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ServiceApproval save(ServiceApproval serviceApproval) {
        return repository.save(serviceApproval);
    }

    @Override
    public Optional<ServiceApproval> update(Long id, ServiceApproval serviceApproval) {
        // Verificar si el objeto existe
        Optional<ServiceApproval> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Actualizar el objeto existente
            serviceApproval.setId(id); // Asegurarse de que el ID sea el mismo
            return Optional.of(repository.save(serviceApproval));
        }
        return Optional.empty(); // Si no existe, retornar vacío
    }

    @Override
    public Optional<ServiceApproval> delete(Long id) {
        Optional<ServiceApproval> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Eliminar si existe
            repository.deleteById(id);
            return existingResponse; // Retornar el objeto eliminado
        }
        return Optional.empty();
     } // Si no existe, retornar vacío    }

}
