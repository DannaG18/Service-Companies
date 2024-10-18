package com.sc.servicecompanies.infrastructure.repositories.emailtype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.EmailTypeService;
import com.sc.servicecompanies.domain.entities.EmailType;

public class EmailTypeImpl implements EmailTypeService{
    @Autowired
    private EmailTypeRepository emailTypeRepository;

    @Transactional
    @Override
    public Optional<EmailType> delete(Long id) {
        Optional<EmailType> emailTypeOp = emailTypeRepository.findById(id);
        emailTypeOp.ifPresent(emailTypeDb -> {
            emailTypeRepository.delete(emailTypeDb);
        });
        return emailTypeOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmailType> findAll() {
        return (List<EmailType>) emailTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<EmailType> findById(Long id) {
        return emailTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public EmailType save(EmailType emailType) {
        return emailTypeRepository.save(emailType);
    }

    @Transactional
    @Override
    public Optional<EmailType> update(Long id, EmailType emailType) {
        Optional<EmailType> emailTypeOld = emailTypeRepository.findById(id);
        if (emailTypeOld.isPresent()) {
            EmailType emailTypeDb = emailTypeOld.orElseThrow();
            emailTypeDb.setNameEmailType(emailType.getNameEmailType());
            return Optional.of(emailTypeRepository.save(emailTypeDb));
        }
        return Optional.empty();
    }
}
