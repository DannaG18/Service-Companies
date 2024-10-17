package com.sc.servicecompanies.infrastructure.repositories.emailperson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.EmailPerson;

@Repository
public interface EmailPersonRepository extends CrudRepository<EmailPerson, Long>{

}
