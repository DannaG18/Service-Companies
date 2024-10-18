package com.sc.servicecompanies.infrastructure.repositories.emailperson;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.EmailPersonService;
import com.sc.servicecompanies.domain.entities.EmailPerson;

@Service
public class EmailPersonImpl implements EmailPersonService{
    @Autowired
    private EmailPersonRepository emailPersonRepository;

    @Transactional
    @Override
    public Optional<EmailPerson> delete(Long id) {
        Optional<EmailPerson> emailPersonOp = emailPersonRepository.findById(id);
        emailPersonOp.ifPresent(emailPersonDb -> {
            emailPersonRepository.delete(emailPersonDb);
        });
        return emailPersonOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmailPerson> findAll() {
        return (List<EmailPerson>) emailPersonRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<EmailPerson> findById(Long id) {
        return emailPersonRepository.findById(id);
    }

    @Transactional
    @Override
    public EmailPerson save(EmailPerson emailPerson) {
        return emailPersonRepository.save(emailPerson);
    }

    @Transactional
    @Override
    public Optional<EmailPerson> update(Long id, EmailPerson emailPerson) {
        Optional<EmailPerson> emailPersonOld = emailPersonRepository.findById(id);
        if (emailPersonOld.isPresent()) {
            EmailPerson emailPersonDb = emailPersonOld.orElseThrow();
            emailPersonDb.setPerson(emailPerson.getPerson());
            emailPersonDb.setEmailType(emailPerson.getEmailType());
            emailPersonDb.setEmail(emailPerson.getEmail());
            return Optional.of(emailPersonRepository.save(emailPersonDb));
        }
        return Optional.empty();
    }
}
