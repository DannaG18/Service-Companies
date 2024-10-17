package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.Services;

import java.util.List;
import java.util.Optional;

public interface ServicesService {

    List<Services> findAll();
    Optional<Services> findById(Long id);
    Services save(Services service);
    Optional<Services> update(Long id, Services service);
    Optional<Services> delete(Long id);
}
