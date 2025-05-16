package com.example.flypark1.Service;

import com.example.flypark1.Model.Taxi;
import com.example.flypark1.Repository.TaxisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiService {

    @Autowired
    private TaxisRepository repository;

    public List<Taxi> getTaxisAdaptados() {
        return repository.findByAdaptado(true);
    }

    public List<Taxi> getTaxisNoAdaptados() {
        return repository.findByAdaptado(false);
    }
    public List<Taxi> findByCiudad(String ciudad) {
        return repository.findByCiudad(ciudad);
    }
}
