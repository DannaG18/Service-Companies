package com.sc.servicecompanies.infrastructure.repositories.persontype;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.PersonType;

@Repository
public interface PersonTypeRepository extends CrudRepository<PersonType, Long>{

}
