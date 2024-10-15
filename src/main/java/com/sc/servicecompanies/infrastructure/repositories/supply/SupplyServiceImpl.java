package com.sc.servicecompanies.infrastructure.repositories.supply;

import com.sc.servicecompanies.application.services.SupplyService;
import com.sc.servicecompanies.domain.entities.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Supply> findAll() {
        return (List<Supply>) supplyRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Supply> findById(Long id) {
        return supplyRepository.findById(id);
    }

    @Transactional
    @Override
    public Supply save(Supply supply) {
        return supplyRepository.save(supply);
    }

    @Transactional
    @Override
    public Optional<Supply> update(Long id, Supply supply) {
        Optional<Supply> supplyOld = supplyRepository.findById(id);
        if(supplyOld.isPresent()) {
            Supply supplyDb = supplyOld.orElseThrow();

            supplyDb.setCodInternal(supply.getCodInternal());
            supplyDb.setSupplyName(supply.getSupplyName());

            return Optional.of(supplyRepository.save(supplyDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Supply> delete(Long id) {
        Optional<Supply> supply = supplyRepository.findById(id);
        supply.ifPresent(supplyDb -> {
            supplyRepository.delete(supplyDb);
        });
        return supply;
    }
}
