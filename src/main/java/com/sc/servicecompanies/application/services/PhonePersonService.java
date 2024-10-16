package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.PhonePerson;

public interface PhonePersonService {
    List<PhonePerson> findAll();
    Optional<PhonePerson> findById(Long id);
    PhonePerson save(PhonePerson phonePerson);
    Optional<PhonePerson> update(Long id, PhonePerson phonePerson);
    Optional<PhonePerson> delete(Long id);
}
