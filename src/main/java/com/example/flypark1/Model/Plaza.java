package com.example.flypark1.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plazas_parking")
public class Plaza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlaza;

    @Column(name = "id_parking")
    private int idParking;

    @Column(name = "numero_plaza")
    private String numeroPlaza;

    public Plaza() {
    }

    public Plaza(int idPlaza, int idParking, String numeroPlaza) {
        this.idPlaza = idPlaza;
        this.idParking = idParking;
        this.numeroPlaza = numeroPlaza;
    }

    public int getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(int idPlaza) {
        this.idPlaza = idPlaza;
    }

    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    
    public String getNumeroPlaza() {
        return numeroPlaza;
    }

    public void setNumeroPlaza(String numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

}
