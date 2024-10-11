package com.sc.servicecompanies.infrastructure.repositories.supply;

import com.sc.servicecompanies.domain.entities.Supply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRepository extends CrudRepository<Supply, Long> {
}
