package com.sc.servicecompanies.infrastructure.repositories.serviceorderstatus;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sc.servicecompanies.application.services.ServiceOrderStatusService;
import com.sc.servicecompanies.domain.entities.ServiceOrderStatus;

public class ServiceOrderStatusImpl implements ServiceOrderStatusService {
    
        
    @Autowired
    private ServiceOrderStatusRepository repository;

    @Override
    public List<ServiceOrderStatus> findAll() {
        return (List<ServiceOrderStatus>) repository.findAll();
    }

    @Override
    public Optional<ServiceOrderStatus> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ServiceOrderStatus save(ServiceOrderStatus ServiceOrder) {
        return repository.save(ServiceOrder);
    }

    @Override
    public Optional<ServiceOrderStatus> update(Long id, ServiceOrderStatus ServiceOrder) {
        // Verificar si el objeto existe
        Optional<ServiceOrderStatus> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Actualizar el objeto existente
            ServiceOrder.setId(id); // Asegurarse de que el ID sea el mismo
            return Optional.of(repository.save(ServiceOrder));
        }
        return Optional.empty(); // Si no existe, retornar vacío
    }

    @Override
    public Optional<ServiceOrderStatus> delete(Long id) {
        Optional<ServiceOrderStatus> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Eliminar si existe
            repository.deleteById(id);
            return existingResponse; // Retornar el objeto eliminado
        }
        return Optional.empty();
     } // Si no existe, retornar vacío    }
}
