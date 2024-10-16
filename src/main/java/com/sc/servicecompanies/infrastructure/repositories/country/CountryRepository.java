package com.sc.servicecompanies.infrastructure.repositories.country;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{

}
