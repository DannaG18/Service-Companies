package com.sc.servicecompanies.infrastructure.repositories.services;

import com.sc.servicecompanies.domain.entities.Services;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends CrudRepository<Services, Long> {
}
