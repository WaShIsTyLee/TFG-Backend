package com.example.flypark1.Controller;

import com.example.flypark1.Model.Plaza;
import com.example.flypark1.Model.Reserva;
import com.example.flypark1.Model.Usuario;
import com.example.flypark1.Service.PlazaService;
import com.example.flypark1.Service.ReservaService;
import com.example.flypark1.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/plaza")
public class PlazaController {

    @Autowired
    PlazaService plazaService;
    @Autowired
    ReservaService reservaService;
    @Autowired
    UsuarioService usuarioService;

    /**
     * Busca plazas disponibles en un parking durante un rango de fechas.
     *
     * @param idParking  ID del parking.
     * @param fechaInicio Fecha y hora de inicio del rango.
     * @param fechaFin   Fecha y hora de fin del rango.
     * @return Lista de plazas disponibles.
     */
    @CrossOrigin
    @GetMapping("/plazasDisponibles")
    public List<Plaza> searchPlazasDisponibles(
            @RequestParam int idParking,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {
        System.out.println("ID Parking recibido en el controlador: " + idParking);
        System.out.println("Fecha inicio recibida: " + fechaInicio);
        System.out.println("Fecha fin recibida: " + fechaFin);

        fechaInicio = fechaInicio.replace("Z", "");
        fechaFin = fechaFin.replace("Z", "");

        LocalDateTime startDate = LocalDateTime.parse(fechaInicio);
        LocalDateTime endDate = LocalDateTime.parse(fechaFin);

        List<Plaza> plazas = reservaService.findPlazasDisponiblesEnRango(idParking, startDate, endDate);
        System.out.println("Plazas encontradas: " + plazas);

        return plazas;
    }

    /**
     * Busca una plaza por su ID.
     *
     * @param idPlaza ID de la plaza.
     * @return Objeto Plaza si se encuentra.
     */
    @CrossOrigin
    @GetMapping("/plaza/{idPlaza}")
    public Plaza findPlazaById(@PathVariable int idPlaza) {
        return plazaService.findPlazaById(idPlaza);
    }

    /**
     * Elimina una plaza y sus reservas asociadas.
     * Si hay reservas futuras, se devuelve el dinero al usuario.
     *
     * @param idPlaza ID de la plaza a eliminar.
     * @return Mensaje indicando el resultado de la operación.
     */
    @CrossOrigin
    @DeleteMapping("/delete/{idPlaza}")
    public ResponseEntity<String> deletePlaza(@PathVariable int idPlaza) {
        try {
            LocalDateTime ahora = LocalDateTime.now();
            List<Reserva> todasLasReservas = reservaService.findAllByIdPlaza(idPlaza);

            for (Reserva reserva : todasLasReservas) {
                Usuario usuario = usuarioService.getUserById(reserva.getIdUsuario());

                if (usuario != null && reserva.getDiaEntrada().isAfter(ahora)) {
                    LocalDateTime fechaEntrada = reserva.getDiaEntrada();
                    LocalDateTime fechaSalida = reserva.getDiaSalida();
                    long horas = ChronoUnit.HOURS.between(fechaEntrada, fechaSalida);
                    if (fechaEntrada.plusHours(horas).isBefore(fechaSalida)) {
                        horas++;
                    }
                    double costo = horas * 0.20;

                    usuario.setMonedero(usuario.getMonedero() + costo);
                    usuarioService.updateUsuario(usuario);
                }

                reservaService.deleteReserva(reserva);
            }

            boolean deleted = plazaService.deletePlaza(idPlaza);
            if (deleted) {
                return ResponseEntity.ok("Plaza y todas las reservas eliminadas correctamente. Dinero devuelto por reservas futuras.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plaza no encontrada.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar plaza: " + e.getMessage());
        }
    }

    /**
     * Busca todas las plazas asociadas a un parking específico.
     *
     * @param idParking ID del parking.
     * @return Lista de plazas asociadas a ese parking.
     */
    @CrossOrigin
    @GetMapping("/porParking/{idParking}")
    public List<Plaza> findPlazasByParking(@PathVariable int idParking) {
        System.out.println("Buscando plazas para parking con ID: " + idParking);
        return plazaService.findPlazasByParking(idParking);
    }

}
