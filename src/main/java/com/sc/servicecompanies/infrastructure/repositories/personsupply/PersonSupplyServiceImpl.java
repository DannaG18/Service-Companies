package com.sc.servicecompanies.infrastructure.repositories.personsupply;

import com.sc.servicecompanies.application.services.PersonSupplyService;
import com.sc.servicecompanies.domain.entities.PersonSupply;
import com.sc.servicecompanies.domain.entities.fkclass.PersonSupplyId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonSupplyServiceImpl implements PersonSupplyService {
    @Autowired
    private PersonSupplyRepository personSupplyRepository;

    @Transactional
    @Override
    public Optional<PersonSupply> delete(PersonSupplyId id) {
        Optional<PersonSupply> personSupplyOp = personSupplyRepository.findById(id);
        personSupplyOp.ifPresent(personSupplyDb -> {
            personSupplyRepository.delete(personSupplyDb);
        });
        return personSupplyOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonSupply> findAll() {
        return (List<PersonSupply>) personSupplyRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PersonSupply> findById(PersonSupplyId id) {
        return personSupplyRepository.findById(id);
    }

    @Transactional
    @Override
    public PersonSupply save(PersonSupply personSupply) {
        return personSupplyRepository.save(personSupply);
    }

    @Transactional
    @Override
    public Optional<PersonSupply> update(PersonSupplyId id, PersonSupply personSupply) {
        Optional<PersonSupply> personSupplyOld = personSupplyRepository.findById(id);
        if (personSupplyOld.isPresent()) {
            PersonSupply personSupplyDb = personSupplyOld.orElseThrow();
            personSupplyDb.setSupply(personSupply.getSupply());
            personSupplyDb.setPerson(personSupply.getPerson());
            personSupplyDb.setService(personSupply.getService());
            personSupplyDb.setDescripcion(personSupply.getDescripcion());
            return Optional.of(personSupplyRepository.save(personSupplyDb));
        }
        return Optional.empty();
    } 
}
