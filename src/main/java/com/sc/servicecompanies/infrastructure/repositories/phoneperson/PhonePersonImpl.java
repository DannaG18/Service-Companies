package com.sc.servicecompanies.infrastructure.repositories.phoneperson;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.PhonePersonService;
import com.sc.servicecompanies.domain.entities.PhonePerson;

@Service
public class PhonePersonImpl implements PhonePersonService{
    @Autowired
    private PhonePersonRepository phonePersonRepository;

    @Transactional
    @Override
    public Optional<PhonePerson> delete(Long id) {
        Optional<PhonePerson> phonePersonOp = phonePersonRepository.findById(id);
        phonePersonOp.ifPresent(phonePersonDb -> {
            phonePersonRepository.delete(phonePersonDb);
        });
        return phonePersonOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PhonePerson> findAll() {
        return (List<PhonePerson>) phonePersonRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PhonePerson> findById(Long id) {
        return phonePersonRepository.findById(id);
    }

    @Transactional
    @Override
    public PhonePerson save(PhonePerson phonePerson) {
        return phonePersonRepository.save(phonePerson);
    }

    @Transactional
    @Override
    public Optional<PhonePerson> update(Long id, PhonePerson phonePerson) {
        Optional<PhonePerson> phonePersonOld = phonePersonRepository.findById(id);
        if (phonePersonOld.isPresent()) {
            PhonePerson phonePersonDb = phonePersonOld.orElseThrow();
            phonePersonDb.setDocumentNumber(phonePerson.getDocumentNumber());
            phonePersonDb.setPhoneTypeId(phonePerson.getPhoneTypeId());
            phonePersonDb.setPhone(phonePerson.getPhone());
            return Optional.of(phonePersonRepository.save(phonePersonDb));
        }
        return Optional.empty();
    }   
}
