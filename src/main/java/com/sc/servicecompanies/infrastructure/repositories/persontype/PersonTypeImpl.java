package com.sc.servicecompanies.infrastructure.repositories.persontype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.PersonTypeService;
import com.sc.servicecompanies.domain.entities.PersonType;

@Service
public class PersonTypeImpl implements PersonTypeService{
    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Transactional
    @Override
    public Optional<PersonType> delete(Long id) {
        Optional<PersonType> personTypeOp = personTypeRepository.findById(id);
        personTypeOp.ifPresent(personTypeDb -> {
            personTypeRepository.delete(personTypeDb);
        });
        return personTypeOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonType> findAll() {
        return (List<PersonType>) personTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PersonType> findById(Long id) {
        return personTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public PersonType save(PersonType personType) {
        return personTypeRepository.save(personType);
    }

    @Transactional
    @Override
    public Optional<PersonType> update(Long id, PersonType personType) {
        Optional<PersonType> personTypeOld = personTypeRepository.findById(id);
        if (personTypeOld.isPresent()) {
            PersonType personTypeDb = personTypeOld.orElseThrow();
            personTypeDb.setNamePersonType(personType.getNamePersonType());
            return Optional.of(personTypeRepository.save(personTypeDb));
        }
        return Optional.empty();
        
    }
}
