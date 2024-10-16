package com.sc.servicecompanies.infrastructure.repositories.companyservice;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.CompanyService;
import com.sc.servicecompanies.domain.entities.fkclass.CompanyServiceId;

public interface CompanyServiceRepository extends CrudRepository<CompanyService, CompanyServiceId>{

}
