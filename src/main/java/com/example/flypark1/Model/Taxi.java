package com.example.flypark1.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "taxis")
public class Taxi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taxi")
    private Long idTaxi;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "adaptado")
    private boolean adaptado;

    @Column(name = "ciudad")
    private String ciudad;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    // Getters y Setters
    public Long getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(Long idTaxi) {
        this.idTaxi = idTaxi;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isAdaptado() {
        return adaptado;
    }

    public void setAdaptado(boolean adaptado) {
        this.adaptado = adaptado;
    }

}
