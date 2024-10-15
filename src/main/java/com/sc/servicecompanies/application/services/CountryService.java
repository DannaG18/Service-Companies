package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.Country;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Country save(Country country);
    Optional<Country> update(Long id, Country country);
    Optional<Country> delete(Country country);
}
