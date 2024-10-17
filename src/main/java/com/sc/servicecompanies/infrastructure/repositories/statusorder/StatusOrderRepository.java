package com.sc.servicecompanies.infrastructure.repositories.statusorder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.StatusOrder;

@Repository
public interface StatusOrderRepository extends CrudRepository<StatusOrder, Long>{

}
