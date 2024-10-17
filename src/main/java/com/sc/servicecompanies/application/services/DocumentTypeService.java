package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.DocumentType;

public interface DocumentTypeService {
    List<DocumentType> findAll();
    Optional<DocumentType> findById(Long id);
    DocumentType save(DocumentType documentType);
    Optional<DocumentType> update(Long id, DocumentType documentType);
    Optional<DocumentType> delete(Long id);
}
