package com.example.flypark1.Controller;

import com.example.flypark1.Model.Parking;
import com.example.flypark1.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @CrossOrigin
    @GetMapping("/parkings")
    public ArrayList<Parking> getParkings() {
        return parkingService.getParkings();
    }

    @CrossOrigin
    @GetMapping("/parkings/{id}")
    public Parking getParking(@PathVariable("id") int id_parking) {
        return parkingService.getParking(id_parking);
    }

}
