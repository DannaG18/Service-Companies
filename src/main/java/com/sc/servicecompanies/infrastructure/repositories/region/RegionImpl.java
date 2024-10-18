package com.sc.servicecompanies.infrastructure.repositories.region;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.RegionService;
import com.sc.servicecompanies.domain.entities.Region;

@Service
public class RegionImpl implements RegionService{
    @Autowired
    private RegionRepository regionRepository;

    @Transactional
    @Override
    public Optional<Region> delete(Long id) {
        Optional<Region> regionOp = regionRepository.findById(id);
        regionOp.ifPresent(regionDb -> {
            regionRepository.delete(regionDb);
        });
        return regionOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Region> findAll() {
        return (List<Region>) regionRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Region> findById(Long id) {
        return regionRepository.findById(id);
    }

    @Transactional
    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Transactional
    @Override
    public Optional<Region> update(Long id, Region region) {
        Optional<Region> regionOld = regionRepository.findById(id);
        if (regionOld.isPresent()) {
            Region regionDb = regionOld.orElseThrow();
            regionDb.setCountry(region.getCountry());
            regionDb.setNameRegion(region.getNameRegion());
            return Optional.of(regionRepository.save(regionDb));
        }
        return Optional.empty();
    }   
}
