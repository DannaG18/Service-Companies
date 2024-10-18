package com.sc.servicecompanies.infrastructure.repositories.statuserviceorder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.StatusServiceOrder;

@Repository
public interface StatusServiceOrderRepository extends CrudRepository<StatusServiceOrder, Long>{

}
