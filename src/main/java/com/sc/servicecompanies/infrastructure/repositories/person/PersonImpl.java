package com.sc.servicecompanies.infrastructure.repositories.person;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.PersonService;
import com.sc.servicecompanies.domain.entities.Person;

@Service
public class PersonImpl implements PersonService{
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @Override
    public Optional<Person> delete(String id) {
        Optional<Person> personOp = personRepository.findById(id);
        personOp.ifPresent(personDb -> {
            personRepository.delete(personDb);
        });
        return personOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    @Transactional
    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public Optional<Person> update(String id, Person person) {
        Optional<Person> personOld = personRepository.findById(id);
        if (personOld.isPresent()) {
            Person personDb = personOld.orElseThrow();
            personDb.setDocumentNumber(person.getDocumentNumber());
            personDb.setPersonType(person.getPersonType());
            personDb.setName(person.getName());
            personDb.setLastName(person.getLastName());
            personDb.setRegistrationDate(person.getRegistrationDate());
            personDb.setDocumentType(person.getDocumentType());
            personDb.setBranch(person.getBranch());
            return Optional.of(personRepository.save(personDb));
        }
        return Optional.empty();
    } 
}
