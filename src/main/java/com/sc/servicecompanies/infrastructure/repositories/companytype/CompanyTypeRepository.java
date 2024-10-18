package com.sc.servicecompanies.infrastructure.repositories.companytype;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.CompanyType;

@Repository
public interface CompanyTypeRepository extends CrudRepository<CompanyType, Long>{

}