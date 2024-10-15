package com.sc.servicecompanies.infrastructure.repositories.serviceorder;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sc.servicecompanies.application.services.ServiceOrderService;
import com.sc.servicecompanies.domain.entities.ServiceOrder;

public class ServiceOrderImpl implements ServiceOrderService{
    
    @Autowired
    private ServiceOrderRepository repository;

    @Override
    public List<ServiceOrder> findAll() {
        return (List<ServiceOrder>) repository.findAll();
    }

    @Override
    public Optional<ServiceOrder> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ServiceOrder save(ServiceOrder ServiceOrder) {
        return repository.save(ServiceOrder);
    }

    @Override
    public Optional<ServiceOrder> update(Long id, ServiceOrder ServiceOrder) {
        // Verificar si el objeto existe
        Optional<ServiceOrder> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Actualizar el objeto existente
            ServiceOrder.setNroOrden(id); // Asegurarse de que el ID sea el mismo
            return Optional.of(repository.save(ServiceOrder));
        }
        return Optional.empty(); // Si no existe, retornar vacío
    }

    @Override
    public Optional<ServiceOrder> delete(Long id) {
        Optional<ServiceOrder> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Eliminar si existe
            repository.deleteById(id);
            return existingResponse; // Retornar el objeto eliminado
        }
        return Optional.empty();
     } // Si no existe, retornar vacío    }
}
