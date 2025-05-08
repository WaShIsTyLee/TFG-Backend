package com.example.flypark1.Repository;

import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findReservasByIdUsuario(Integer idUsuario);

    @Query("""
    SELECT p FROM Plaza p
    WHERE p.idParking = :idParking AND NOT EXISTS (
        SELECT r FROM Reserva r
        WHERE r.idPlaza = p.idPlaza AND
              r.diaEntrada < :fechaFin AND
              r.diaSalida > :fechaInicio
    )
""")
    List<Plaza> findPlazasDisponiblesEnRango(
            @Param("idParking") int idParking,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );

    @Query("SELECT r FROM Reserva r WHERE r.idUsuario = :idUsuario AND r.diaEntrada >= :hoy")
    List<Reserva> findReservasActivas(@Param("idUsuario") Integer idUsuario, @Param("hoy") LocalDateTime hoy);

    @Query("SELECT r FROM Reserva r WHERE r.idUsuario = :idUsuario AND r.matricula = :matricula AND r.diaSalida > :fechaActual")
    List<Reserva> findReservasActivasPorMatricula(
            @Param("idUsuario") Integer idUsuario,
            @Param("matricula") String matricula,
            @Param("fechaActual") LocalDateTime fechaActual
    );

}
