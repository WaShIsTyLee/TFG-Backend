package com.example.flypark1.Service;

import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Model.Reserva;
import com.example.flypark1.Repository.PlazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlazaService {
    @Autowired
    private PlazaRepository repository;

    public Plaza updatePlaza(Plaza plaza) {
        return repository.save(plaza);
    }

    public List<Plaza> getPlazas() {
        return repository.findAll();
    }

    public Plaza findPlazaById(int idPlaza) {
        return repository.findByIdPlaza(idPlaza);
    }

    public Plaza createPlaza(Plaza plaza) {
        return repository.save(plaza);
    }

    public boolean deletePlaza(int idPlaza) {
        if (repository.existsById(idPlaza)) {
            repository.deleteById(idPlaza);
            return true;
        }
        return false;
    }
    public List<Plaza> findPlazasByParking(int idParking) {
        return repository.findByIdParking(idParking);
    }






}
