package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.StatusServiceOrder;

public interface StatusServiceOrderService {
    List<StatusServiceOrder> findAll();
    Optional<StatusServiceOrder> findById(Long id);
    StatusServiceOrder save(StatusServiceOrder statusServiceOrder);
    Optional<StatusServiceOrder> update(Long id, StatusServiceOrder statusServiceOrder);
    Optional<StatusServiceOrder> delete(Long id);
}
