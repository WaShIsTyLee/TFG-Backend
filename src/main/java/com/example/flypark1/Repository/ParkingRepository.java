package com.example.flypark1.Repository;

import com.example.flypark1.Model.Parking;
import com.example.flypark1.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {

    Parking findByIdParking(int id_parking);
}
