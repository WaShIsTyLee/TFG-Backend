package com.example.flypark1.Controller;

import com.example.flypark1.Model.Taxi;
import com.example.flypark1.Service.TaxiService;
import com.example.flypark1.Service.UsuarioService;
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

    @CrossOrigin
    @RequestMapping("/getTaxisAdaptados")
    public List<Taxi> getTaxisAdaptados() {
        return taxiService.getTaxisAdaptados();
    }

    @CrossOrigin
    @RequestMapping("/getTaxisNoAdaptados")
    public List<Taxi> getTaxisNoAdaptados() {
        return taxiService.getTaxisNoAdaptados();
    }

    @CrossOrigin
    @RequestMapping("/getTaxisByCiudad")
    public List<Taxi> getTaxisByCiudad(String ciudad) {
        return taxiService.findByCiudad(ciudad);
    }



}
