package com.sc.servicecompanies.infrastructure.repositories.person;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.Person;

public interface PersonRepository extends CrudRepository<Person, String>{
    
}
