package com.example.flypark1.Service;

import com.example.flypark1.Model.Parking;
import com.example.flypark1.Repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepository repository;

    public ArrayList<Parking> getParkings() {
        return (ArrayList<Parking>) repository.findAll();
    }
    public Parking getParking(int id) {
        return repository.findById(id).get();
    }
}
