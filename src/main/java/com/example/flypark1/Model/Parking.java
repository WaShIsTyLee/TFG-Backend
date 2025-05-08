package com.example.flypark1.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParking;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    // Nuevo campo para latitud
    @Column(name = "latitud")
    private Double latitud;

    // Nuevo campo para longitud
    @Column(name = "longitud")
    private Double longitud;

    // Constructor con los nuevos campos
    public Parking(int idParking, String nombre, String ubicacion, Double latitud, Double longitud) {
        this.idParking = idParking;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Constructor vacío
    public Parking() {

    }

    // Getters y setters para los nuevos campos
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    // Otros getters y setters existentes
    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int id_parking) {
        this.idParking = id_parking;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
