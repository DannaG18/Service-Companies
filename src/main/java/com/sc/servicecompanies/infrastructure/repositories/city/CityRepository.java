package com.sc.servicecompanies.infrastructure.repositories.city;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
