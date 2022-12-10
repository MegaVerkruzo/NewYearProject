package com.commercial.backend.service;

import com.commercial.backend.model.City;
import com.commercial.backend.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService{
    private CityRepository repository;

    CityService(CityRepository cityRepository) {
        this.repository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        var cities = (List<City>) repository.findAll();

        return cities;
    }
}
