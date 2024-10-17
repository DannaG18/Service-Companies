package com.sc.servicecompanies.infrastructure.repositories.documentype;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.DocumentType;

@Repository
public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long>{

}
