package com.example.flypark1.Model;

import jakarta.persistence.*;


import java.util.Date;

@Entity
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Long idVuelo;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_salida")
    private Date fechaSalida;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_llegada")
    private Date fechaLlegada;

    // Getters y Setters
    public Long getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Long idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
}
