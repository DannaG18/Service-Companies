package com.sc.servicecompanies.infrastructure.repositories.servicesupply;

import com.sc.servicecompanies.domain.entities.ServiceSupply;
import com.sc.servicecompanies.domain.entities.fkclass.ServiceSupplyId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceSupplyRepository extends CrudRepository<ServiceSupply, ServiceSupplyId> {

}
