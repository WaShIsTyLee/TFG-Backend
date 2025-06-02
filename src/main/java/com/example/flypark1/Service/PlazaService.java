package com.example.flypark1.Service;

import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Repository.PlazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlazaService {
    @Autowired
    private PlazaRepository repository;


    /**
     * Busca una plaza por su ID.
     *
     * @param idPlaza ID de la plaza a buscar.
     * @return Plaza encontrada, o null si no existe.
     */
    public Plaza findPlazaById(int idPlaza) {
        return repository.findByIdPlaza(idPlaza);
    }

    /**
     * Crea una nueva plaza y la guarda en la base de datos.
     *
     * @param plaza Objeto Plaza con los datos a guardar.
     * @return Plaza creada y guardada.
     */
    public Plaza createPlaza(Plaza plaza) {
        return repository.save(plaza);
    }

    /**
     * Elimina una plaza por su ID si existe.
     *
     * @param idPlaza ID de la plaza a eliminar.
     * @return true si se eliminó correctamente, false si no se encontró.
     */
    public boolean deletePlaza(int idPlaza) {
        if (repository.existsById(idPlaza)) {
            repository.deleteById(idPlaza);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todas las plazas que pertenecen a un parking específico.
     *
     * @param idParking ID del parking.
     * @return Lista de plazas asociadas al parking dado.
     */
    public List<Plaza> findPlazasByParking(int idParking) {
        return repository.findByIdParking(idParking);
    }
}
