package com.sc.servicecompanies.infrastructure.repositories.documentype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.DocumentTypeService;
import com.sc.servicecompanies.domain.entities.DocumentType;

@Service
public class DocumentTypeImpl implements DocumentTypeService{
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Transactional
    @Override
    public Optional<DocumentType> delete(Long id) {
        Optional<DocumentType> documentTypeOp = documentTypeRepository.findById(id);
        documentTypeOp.ifPresent(documentTypeDb -> {
            documentTypeRepository.delete(documentTypeDb);
        });
        return documentTypeOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentType> findAll() {
        return (List<DocumentType>) documentTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DocumentType> findById(Long id) {
        return documentTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    @Transactional
    @Override
    public Optional<DocumentType> update(Long id, DocumentType documentType) {
        Optional<DocumentType> documentTypeOld = documentTypeRepository.findById(id);
        if (documentTypeOld.isPresent()) {
            DocumentType documentTypeDb = documentTypeOld.orElseThrow();
            documentTypeDb.setNameDocumentType(documentType.getNameDocumentType());
            return Optional.of(documentTypeRepository.save(documentTypeDb));
        }
        return Optional.empty();
    }
}
