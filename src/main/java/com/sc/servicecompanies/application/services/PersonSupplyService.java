package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.PersonSupply;
import com.sc.servicecompanies.domain.entities.fkclass.PersonSupplyId;

import java.util.List;
import java.util.Optional;

public interface PersonSupplyService {

    List<PersonSupply> findAll();
    Optional<PersonSupply> findById(PersonSupplyId id);
    PersonSupply save(PersonSupply personSupply);
    Optional<PersonSupply> update(PersonSupplyId id, PersonSupply personSupply);
    Optional<PersonSupply> delete(PersonSupplyId id);
}
