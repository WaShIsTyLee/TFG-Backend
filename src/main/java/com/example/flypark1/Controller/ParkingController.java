package com.example.flypark1.Controller;

import com.example.flypark1.Model.Parking;
import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Service.ParkingService;
import com.example.flypark1.Service.PlazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    private PlazaService p;

    /**
     * Obtener todos los parkings registrados
     * @return Lista de parkings
     */
    @CrossOrigin
    @GetMapping("/parkings")
    public ArrayList<Parking> getParkings() {
        return parkingService.getParkings();
    }

    /**
     * Obtener un parking por su ID
     * @param id_parking ID del parking a buscar
     * @return Objeto Parking encontrado
     */
    @CrossOrigin
    @GetMapping("/parkings/{id}")
    public Parking getParking(@PathVariable("id") int id_parking) {
        return parkingService.getParking(id_parking);
    }

    /**
     * Creación de un nuevo parking
     * @param parking Objeto Parking con la información a registrar
     * @return Parking creado
     */
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<Parking> createParking(@RequestBody Parking parking) {
        Parking nuevoParking = parkingService.createParking(parking);
        return ResponseEntity.ok(nuevoParking);
    }

    /**
     * Eliminar parking mediante el id_parking
     * @param id_parking ID del parking a eliminar
     * @return Mensaje con el resultado de la operación
     */
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteParking(@PathVariable("id") int id_parking) {
        boolean deleted = parkingService.deleteParking(id_parking);
        if (deleted) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Parking deleted successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Parking not found"));
        }
    }

    /**
     * Crear una plaza para el parking mediante el id_parking presente en Plaza
     * @param plaza Objeto Plaza con los datos a registrar
     * @return Plaza creada
     */
    @CrossOrigin
    @PostMapping("/plaza/create")
    public ResponseEntity<Plaza> createPlaza(@RequestBody Plaza plaza) {
        Plaza nuevaPlaza = p.createPlaza(plaza);
        return ResponseEntity.ok(nuevaPlaza);
    }

}
