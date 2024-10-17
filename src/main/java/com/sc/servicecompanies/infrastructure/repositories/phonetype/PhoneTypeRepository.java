package com.sc.servicecompanies.infrastructure.repositories.phonetype;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.PhoneType;

@Repository
public interface PhoneTypeRepository extends CrudRepository<PhoneType, Long>{

}
