package com.sc.servicecompanies.infrastructure.repositories.person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String>{
    
}
