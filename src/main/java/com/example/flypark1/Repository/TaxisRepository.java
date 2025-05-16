package com.example.flypark1.Repository;

import com.example.flypark1.Model.Reserva;
import com.example.flypark1.Model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxisRepository extends JpaRepository<Taxi, Integer> {

    List<Taxi> findByAdaptado(boolean adaptado);

    List<Taxi> findByCiudad(String ciudad);
}
