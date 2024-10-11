package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.Supply;

import java.util.List;
import java.util.Optional;

public interface SupplyService {

    List<Supply> findAll();

    Optional<Supply> findById(Long id);

    Supply save(Supply supply);

    Optional<Supply> update(Long id, Supply supply);

    Optional<Supply> delete(Long id);
}
