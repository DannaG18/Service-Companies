package com.sc.servicecompanies.infrastructure.repositories.region;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.Region;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long>{

}
