package com.sc.servicecompanies.infrastructure.repositories.company;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>{

}
