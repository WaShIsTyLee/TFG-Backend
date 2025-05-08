package com.example.flypark1.Model;

import jakarta.persistence.*;


@Entity
public class Taxi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taxi")
    private Long idTaxi;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "matricula")
    private String matricula;

    // Getters y Setters
    public Long getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(Long idTaxi) {
        this.idTaxi = idTaxi;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
