package com.sc.servicecompanies.infrastructure.repositories.emailtype;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.EmailType;

@Repository
public interface EmailTypeRepository extends CrudRepository<EmailType, Long>{

}
