package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.EmailType;

public interface EmailTypeService {
    List<EmailType> findAll();
    Optional<EmailType> findById(Long id);
    EmailType save(EmailType emailType);
    Optional<EmailType> update(Long id, EmailType emailType);
    Optional<EmailType> delete(Long id);
}
