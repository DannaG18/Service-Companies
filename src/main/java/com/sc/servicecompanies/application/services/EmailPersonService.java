package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.EmailPerson;

public interface EmailPersonService {
    List<EmailPerson> findAll();
    Optional<EmailPerson> findById(Long id);
    EmailPerson save(EmailPerson emailPerson);
    Optional<EmailPerson> update(Long id, EmailPerson emailPerson);
    Optional<EmailPerson> delete(Long id);
}
