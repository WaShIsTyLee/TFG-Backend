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

    // Crear una nueva reserva de plaza de parking
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Buscar una reserva por ID
    public Reserva obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    // Eliminar una reserva por ID
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
        LocalDateTime hoy = LocalDateTime.now();  // Obtienes la fecha y hora actuales
        return reservaRepository.findReservasActivas(idUsuario, hoy);
    }
    public boolean existeReservaActivaConMatricula(Integer idUsuario, String matricula) {
        LocalDateTime ahora = LocalDateTime.now();
        List<Reserva> reservas = reservaRepository.findReservasActivasPorMatricula(idUsuario, matricula, ahora);
        return !reservas.isEmpty();
    }
}
