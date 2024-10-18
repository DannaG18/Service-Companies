package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.PersonType;

public interface PersonTypeService {
    List<PersonType> findAll();
    Optional<PersonType> findById(Long id);
    PersonType save(PersonType personType);
    Optional<PersonType> update(Long id, PersonType personType);
    Optional<PersonType> delete(Long id);
}
