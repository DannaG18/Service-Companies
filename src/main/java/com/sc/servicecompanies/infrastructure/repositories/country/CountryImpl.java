package com.sc.servicecompanies.infrastructure.repositories.country;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.CountryService;
import com.sc.servicecompanies.domain.entities.Country;

@Service
public class CountryImpl implements CountryService{
    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    @Override
    public Optional<Country> delete(Long id) {
        Optional<Country> countryOp = countryRepository.findById(id);
        countryOp.ifPresent(countryDb -> {
            countryRepository.delete(countryDb);
        });
        return countryOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Transactional
    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    @Override
    public Optional<Country> update(Long id, Country country) {
        Optional<Country> countryOld = countryRepository.findById(id);
        if (countryOld.isPresent()) {
            Country countryDb = countryOld.orElseThrow();
            countryDb.setNameCountry(country.getNameCountry());
            return Optional.of(countryRepository.save(countryDb));
        }
        return Optional.empty();
    }
}
