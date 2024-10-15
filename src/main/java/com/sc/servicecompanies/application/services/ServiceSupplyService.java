package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.ServiceSupply;
import com.sc.servicecompanies.domain.entities.fkclass.ServiceSupplyId;

import java.util.List;
import java.util.Optional;

public interface ServiceSupplyService {

    List<ServiceSupply> findAll();
    Optional<ServiceSupply> findById(ServiceSupplyId id);
    ServiceSupply save(ServiceSupply serviceSupply);
    Optional<ServiceSupply> update(ServiceSupplyId id, ServiceSupply serviceSupply);
    Optional<ServiceSupply> delete(ServiceSupplyId id);
}
