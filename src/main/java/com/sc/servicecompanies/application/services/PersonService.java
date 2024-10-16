package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.Person;
public interface PersonService {

    List<Person> findAll();
    Optional<Person> findById(String id);
    Person save(Person person);
    Optional<Person> update(String id, Person person);
    Optional<Person> delete(String id);

}
