package com.example.flypark1.Service;

import com.example.flypark1.Model.Parking;
import com.example.flypark1.Repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepository repository;

    /**
     * Obtiene una lista con todos los parkings disponibles.
     *
     * @return ArrayList con todos los parkings.
     */
    public ArrayList<Parking> getParkings() {
        return (ArrayList<Parking>) repository.findAll();
    }

    /**
     * Busca un parking por su ID.
     *
     * @param id ID del parking a buscar.
     * @return Parking encontrado.
     */
    public Parking getParking(int id) {
        return repository.findById(id).get();
    }

    /**
     * Crea un nuevo parking y lo guarda en la base de datos.
     *
     * @param parking Objeto Parking con los datos a guardar.
     * @return Parking guardado.
     */
    public Parking createParking(Parking parking) {
        return repository.save(parking);
    }

    /**
     * Elimina un parking por su ID si existe.
     *
     * @param idParking ID del parking a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean deleteParking(int idParking) {
        Optional<Parking> optionalParking = repository.findById(idParking);
        if (optionalParking.isPresent()) {
            repository.deleteById(idParking);
            return true;
        } else {
            return false;
        }
    }
}
