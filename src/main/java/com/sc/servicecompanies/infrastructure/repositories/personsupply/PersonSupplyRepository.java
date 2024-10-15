package com.sc.servicecompanies.infrastructure.repositories.personsupply;

import com.sc.servicecompanies.domain.entities.PersonSupply;
import com.sc.servicecompanies.domain.entities.fkclass.PersonSupplyId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonSupplyRepository extends CrudRepository<PersonSupply, PersonSupplyId> {
}
