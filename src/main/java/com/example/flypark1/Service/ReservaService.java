package com.example.flypark1.Service;

import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Model.Reserva;
import com.example.flypark1.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReservaPlaza(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva crearReservaTaxi(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }


    public boolean eliminarReserva(Integer id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Reserva> getReservasbyIdUsuario(Integer id) {
        return reservaRepository.findReservasByIdUsuario(id);
    }
    public List<Plaza> findPlazasDisponiblesEnRango(int idParking, LocalDateTime startDate, LocalDateTime endDate) {
        return reservaRepository.findPlazasDisponiblesEnRango(idParking, startDate, endDate);
    }
    public List<Reserva> getReservasActivas(Integer idUsuario) {
        LocalDateTime hoy = LocalDateTime.now();
        return reservaRepository.findReservasActivas(idUsuario, hoy);
    }
    public boolean existeReservaActivaConMatricula(Integer idUsuario, String matricula) {
        LocalDateTime ahora = LocalDateTime.now();
        List<Reserva> reservas = reservaRepository.findReservasActivasPorMatricula(idUsuario, matricula, ahora);
        return !reservas.isEmpty();
    }
    public Reserva findReservaByIdPlaza(int idPlaza) {
        Optional<Reserva> reservaOpt = reservaRepository.findByIdPlaza(idPlaza);
        return reservaOpt.orElse(null);
    }
        public boolean deleteReserva(Reserva reserva) {
            if (reservaRepository.existsById(reserva.getIdReserva())) {
                reservaRepository.delete(reserva);
                return true;
            }
            return false;
        }

    public List<Reserva> findReservasActivasByIdPlaza(int idPlaza) {
        LocalDateTime ahora = LocalDateTime.now();
        return reservaRepository.findReservasActivasByIdPlaza(idPlaza, ahora);
    }
    public List<Reserva> findReservasInactivasByIdPlaza(int idPlaza, LocalDateTime ahora) {
        return reservaRepository.findReservasInactivasByIdPlaza(idPlaza, ahora);
    }
    public List<Reserva> findAllByIdPlaza(int idPlaza) {
        return reservaRepository.findAllByIdPlaza(idPlaza);
    }




}
