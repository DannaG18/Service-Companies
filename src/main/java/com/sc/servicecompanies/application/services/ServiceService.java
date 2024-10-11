package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    List<Service> findAll();

    Optional<Service> findById(Long id);

    Service save(Service service);

    Optional<Service> update(Long id, Service service);

    Optional<Service> delete(Long id);
}
