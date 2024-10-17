package com.sc.servicecompanies.infrastructure.repositories.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{

}
