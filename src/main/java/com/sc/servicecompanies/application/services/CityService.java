package com.sc.servicecompanies.application.services;

import java.util.List;

import java.util.Optional;

import com.sc.servicecompanies.domain.entities.City;

public interface CityService {
    List<City> findAll();
    Optional<City> findById(Long id);
    City save(City city);
    Optional<City> update(Long id, City city);
    Optional<City> delete(Long id);
}
