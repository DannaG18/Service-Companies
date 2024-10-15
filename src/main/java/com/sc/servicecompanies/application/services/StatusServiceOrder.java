package com.sc.servicecompanies.application.services;

import java.util.Optional;
import java.util.List;

public interface StatusServiceOrder {
    
    List<StatusServiceOrder> findAll();
    Optional<StatusServiceOrder> findById(Long id);
    StatusServiceOrder save(StatusServiceOrder StatusServiceOrder);
    Optional<StatusServiceOrder> update(Long id, StatusServiceOrder StatusServiceOrder);
    Optional<StatusServiceOrder> delete(Long id);

}
