package com.sc.servicecompanies.infrastructure.repositories.country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>{

}
