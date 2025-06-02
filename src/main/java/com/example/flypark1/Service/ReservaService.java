package com.example.flypark1.Service;

import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Model.Reserva;
import com.example.flypark1.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    /**
     * Guarda una nueva reserva de plaza en la base de datos.
     */
    public Reserva crearReservaPlaza(Reserva reserva) {
        return reservaRepository.save(reserva);
    }



    /**
     * Busca una reserva por su ID.
     */
    public Reserva obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    /**
     * Elimina una reserva por su ID.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminarReserva(Integer id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todas las reservas de un usuario.
     */
    public List<Reserva> getReservasbyIdUsuario(Integer id) {
        return reservaRepository.findReservasByIdUsuario(id);
    }

    /**
     * Encuentra plazas disponibles en un rango de fechas para un parking.
     */
    public List<Plaza> findPlazasDisponiblesEnRango(int idParking, LocalDateTime startDate, LocalDateTime endDate) {
        return reservaRepository.findPlazasDisponiblesEnRango(idParking, startDate, endDate);
    }

    /**
     * Obtiene reservas activas de un usuario (reservas cuyo díaSalida es posterior a ahora).
     */
    public List<Reserva> getReservasActivas(Integer idUsuario) {
        LocalDateTime hoy = LocalDateTime.now();
        return reservaRepository.findReservasActivas(idUsuario, hoy);
    }

    /**
     * Verifica si existe una reserva activa para un usuario con la misma matrícula.
     */
    public boolean existeReservaActivaConMatricula(Integer idUsuario, String matricula) {
        LocalDateTime ahora = LocalDateTime.now();
        List<Reserva> reservas = reservaRepository.findReservasActivasPorMatricula(idUsuario, matricula, ahora);
        return !reservas.isEmpty();
    }

    /**
     * Elimina una reserva si existe.
     */
    public boolean deleteReserva(Reserva reserva) {
        if (reservaRepository.existsById(reserva.getIdReserva())) {
            reservaRepository.delete(reserva);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todas las reservas asociadas a una plaza específica.
     */
    public List<Reserva> findAllByIdPlaza(int idPlaza) {
        return reservaRepository.findAllByIdPlaza(idPlaza);
    }
}
