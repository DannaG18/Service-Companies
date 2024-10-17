package com.sc.servicecompanies.infrastructure.repositories.phoneperson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.PhonePerson;

@Repository
public interface PhonePersonRepository extends CrudRepository<PhonePerson, Long>{

}
