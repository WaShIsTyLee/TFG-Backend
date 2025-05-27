package com.example.flypark1.Controller;

import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Service.PlazaService;
import com.example.flypark1.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/plaza")
public class PlazaController {

    @Autowired
    PlazaService plazaService;
    @Autowired
    ReservaService reservaService;


    @CrossOrigin
    @GetMapping("/plazasDisponibles")
    public List<Plaza> searchPlazasDisponibles(
            @RequestParam int idParking,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {
        System.out.println("ID Parking recibido en el controlador: " + idParking);
        System.out.println("Fecha inicio recibida: " + fechaInicio);
        System.out.println("Fecha fin recibida: " + fechaFin);


        fechaInicio = fechaInicio.replace("Z", "");
        fechaFin = fechaFin.replace("Z", "");

        LocalDateTime startDate = LocalDateTime.parse(fechaInicio);
        LocalDateTime endDate = LocalDateTime.parse(fechaFin);

        List<Plaza> plazas = reservaService.findPlazasDisponiblesEnRango(idParking, startDate, endDate);
        System.out.println("Plazas encontradas: " + plazas);

        return plazas;
    }

    @CrossOrigin
    @GetMapping("/plaza/{idPlaza}")
    public Plaza findPlazaById(@PathVariable int idPlaza) {
        return plazaService.findPlazaById(idPlaza);
    }

}
