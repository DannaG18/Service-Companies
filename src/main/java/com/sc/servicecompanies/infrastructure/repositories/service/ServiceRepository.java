package com.sc.servicecompanies.infrastructure.repositories.service;

import com.sc.servicecompanies.domain.entities.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
}
