package com.example.flypark1.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    private Integer idUsuario;
    private Integer idParking;
    private Integer idPlaza;

    @Column(name = "dia_entrada")
    private LocalDateTime diaEntrada;

    @Column(name = "dia_salida")
    private LocalDateTime diaSalida;

    @Column(name = "matricula")
    private String matricula;

    // Relaciones con entidades Parking y Plaza
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPlaza", insertable = false, updatable = false)
    private Plaza plaza;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idParking", insertable = false, updatable = false)
    private Parking parking;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_taxi", insertable = false, updatable = false)
    private Taxi taxi;

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    // Constructores
    public Reserva() {
    }

    public Reserva(Integer idUsuario, Integer idParking, Integer idPlaza, LocalDateTime diaEntrada, LocalDateTime diaSalida, String matricula) {
        this.idUsuario = idUsuario;
        this.idParking = idParking;
        this.idPlaza = idPlaza;
        this.diaEntrada = diaEntrada;
        this.diaSalida = diaSalida;
        this.matricula = matricula;
    }

    public Reserva(Integer idReserva, Integer idUsuario, String matricula, Taxi taxi) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.matricula = matricula;
        this.taxi = taxi;
    }

    // Getters y Setters
    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdParking() {
        return idParking;
    }

    public void setIdParking(Integer idParking) {
        this.idParking = idParking;
    }

    public Integer getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(Integer idPlaza) {
        this.idPlaza = idPlaza;
    }

    public LocalDateTime getDiaEntrada() {
        return diaEntrada;
    }

    public void setDiaEntrada(LocalDateTime diaEntrada) {
        this.diaEntrada = diaEntrada;
    }

    public LocalDateTime getDiaSalida() {
        return diaSalida;
    }

    public void setDiaSalida(LocalDateTime diaSalida) {
        this.diaSalida = diaSalida;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
