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

    /**
     * Retorna la lista de taxis adaptados (p.ej. accesibles o con ciertas características especiales).
     */
    public List<Taxi> getTaxisAdaptados() {
        return repository.findByAdaptado(true);
    }

    /**
     * Retorna la lista de taxis que no están adaptados.
     */
    public List<Taxi> getTaxisNoAdaptados() {
        return repository.findByAdaptado(false);
    }

    /**
     * Retorna una lista de taxis que operan en una ciudad específica.
     */
    public List<Taxi> findByCiudad(String ciudad) {
        return repository.findByCiudad(ciudad);
    }
}
