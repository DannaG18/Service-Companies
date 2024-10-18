package com.sc.servicecompanies.infrastructure.repositories.city;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.CityService;
import com.sc.servicecompanies.domain.entities.City;

@Service
public class CityImpl implements CityService{
    @Autowired
    private CityRepository cityRepository;

    @Transactional
    @Override
    public Optional<City> delete(Long id) {
        Optional<City> cityOp = cityRepository.findById(id);
        cityOp.ifPresent(cityDb -> {
            cityRepository.delete(cityDb);
        });
        return cityOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<City> findAll() {
        return (List<City>) cityRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Transactional
    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Transactional
    @Override
    public Optional<City> update(Long id, City city) {
        Optional<City> cityOld = cityRepository.findById(id);
        if (cityOld.isPresent()) {
            City cityDb = cityOld.orElseThrow();
            cityDb.setRegion(city.getRegion());;
            cityDb.setNameCity(city.getNameCity());
            return Optional.of(cityRepository.save(cityDb));
        }
        return Optional.empty();
    }   
}
