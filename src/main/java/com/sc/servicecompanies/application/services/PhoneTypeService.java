package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.PhoneType;

public interface PhoneTypeService {
    List<PhoneType> findAll();
    Optional<PhoneType> findById(Long id);
    PhoneType save(PhoneType phoneType);
    Optional<PhoneType> update(Long id, PhoneType phoneType);
    Optional<PhoneType> delete(Long id);
}
