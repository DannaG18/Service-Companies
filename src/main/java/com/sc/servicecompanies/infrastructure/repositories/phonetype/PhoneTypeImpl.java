package com.sc.servicecompanies.infrastructure.repositories.phonetype;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.PhoneTypeService;
import com.sc.servicecompanies.domain.entities.PhoneType;

@Service
public class PhoneTypeImpl implements PhoneTypeService{
    @Autowired
    private PhoneTypeRepository phoneTypeRepository;

    @Transactional
    @Override
    public Optional<PhoneType> delete(Long id) {
        Optional<PhoneType> phoneTypeOp = phoneTypeRepository.findById(id);
        phoneTypeOp.ifPresent(phoneTypeDb -> {
            phoneTypeRepository.delete(phoneTypeDb);
        });
        return phoneTypeOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PhoneType> findAll() {
        return (List<PhoneType>) phoneTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PhoneType> findById(Long id) {
        return phoneTypeRepository.findById(id);
    }

    @Transactional
    @Override
    public PhoneType save(PhoneType phoneType) {
        return phoneTypeRepository.save(phoneType);
    }

    @Transactional
    @Override
    public Optional<PhoneType> update(Long id, PhoneType phoneType) {
        Optional<PhoneType> phoneTypeOld = phoneTypeRepository.findById(id);
        if (phoneTypeOld.isPresent()) {
            PhoneType phoneTypeDb = phoneTypeOld.orElseThrow();
            phoneTypeDb.setDescription(phoneType.getDescription());
            return Optional.of(phoneTypeRepository.save(phoneTypeDb));
        }
        return Optional.empty();
    }   
}
