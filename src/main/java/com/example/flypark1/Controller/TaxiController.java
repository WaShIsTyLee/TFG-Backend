package com.example.flypark1.Controller;

import com.example.flypark1.Model.Taxi;
import com.example.flypark1.Service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taxis")
public class TaxiController {

    @Autowired
    private TaxiService taxiService;

    /**
     * Obtiene una lista de taxis adaptados para personas con movilidad reducida.
     *
     * @return Lista de taxis adaptados.
     */
    @CrossOrigin
    @RequestMapping("/getTaxisAdaptados")
    public List<Taxi> getTaxisAdaptados() {
        return taxiService.getTaxisAdaptados();
    }

    /**
     * Obtiene una lista de taxis no adaptados.
     *
     * @return Lista de taxis no adaptados.
     */
    @CrossOrigin
    @RequestMapping("/getTaxisNoAdaptados")
    public List<Taxi> getTaxisNoAdaptados() {
        return taxiService.getTaxisNoAdaptados();
    }

    /**
     * Obtiene una lista de taxis disponibles en una ciudad específica.
     *
     * @param ciudad Nombre de la ciudad.
     * @return Lista de taxis disponibles en esa ciudad.
     */
    @CrossOrigin
    @RequestMapping("/getTaxisByCiudad")
    public List<Taxi> getTaxisByCiudad(String ciudad) {
        return taxiService.findByCiudad(ciudad);
    }

}
