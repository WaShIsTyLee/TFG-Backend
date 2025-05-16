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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private PlazaService plazaService;
    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping("/reservar")
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva) {
        try {
            LocalDateTime fechaEntrada = reserva.getDiaEntrada();
            LocalDateTime fechaSalida = reserva.getDiaSalida();

            if (fechaEntrada.isAfter(fechaSalida)) {
                return new ResponseEntity<>("La fecha de entrada debe ser antes que la de salida.", HttpStatus.BAD_REQUEST);
            }

            Plaza plaza = plazaService.findPlazaById(reserva.getIdPlaza());
            if (plaza == null) {
                return new ResponseEntity<>("Plaza no encontrada", HttpStatus.NOT_FOUND);
            }

            boolean existe = reservaService.existeReservaActivaConMatricula(reserva.getIdUsuario(), reserva.getMatricula());
            if (existe) {
                return new ResponseEntity<>("Ya existe una reserva activa con esta matrícula.", HttpStatus.CONFLICT);
            }

            // 1. Calcular duración en horas (redondeado hacia arriba)
            long horas = ChronoUnit.HOURS.between(fechaEntrada, fechaSalida);
            if (fechaEntrada.plusHours(horas).isBefore(fechaSalida)) {
                horas++; // Redondear hacia arriba si hay minutos adicionales
            }

            // 2. Calcular el costo
            double costo = horas * 0.20;

            // 3. Obtener el usuario
            Usuario usuario = usuarioService.getUserById(reserva.getIdUsuario());
            if (usuario == null) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }

            // 4. Verificar que tenga saldo suficiente
            if (usuario.getMonedero() < costo) {
                return new ResponseEntity<>("Saldo insuficiente en el monedero", HttpStatus.BAD_REQUEST);
            }

            // 5. Restar el monto
            usuario.setMonedero(usuario.getMonedero() - costo);
            usuarioService.updateUsuario(usuario); // Asegúrate de que este método actualice el usuario en la base de datos

            // 6. Crear la reserva
            Reserva nuevaReserva = reservaService.crearReservaPlaza(reserva);
            return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la reserva: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping("/reservarTaxi")
    public ResponseEntity<?> crearReservaTaxi(@RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaService.crearReservaTaxi(reserva);
            return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la reserva: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

        LocalDateTime startDate = LocalDateTime.parse(fechaInicio, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endDate = LocalDateTime.parse(fechaFin, DateTimeFormatter.ISO_DATE_TIME);

        List<Plaza> plazas = reservaService.findPlazasDisponiblesEnRango(idParking, startDate, endDate);
        System.out.println("Plazas encontradas: " + plazas);

        return plazas;
    }

    @CrossOrigin
    @GetMapping("/activas/{idUsuario}")
    public ResponseEntity<List<Reserva>> obtenerReservasActivas(@PathVariable Integer idUsuario) {
        List<Reserva> reservasActivas = reservaService.getReservasActivas(idUsuario);
        return new ResponseEntity<>(reservasActivas, HttpStatus.OK);
    }

    // Obtener una reserva por ID
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Integer id) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Reserva>> obtenerReservasPorIdUsuario(@PathVariable Integer id) {
        List<Reserva> reservas = reservaService.getReservasbyIdUsuario(id);
        if (reservas != null && !reservas.isEmpty()) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Integer id) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }

        // Obtener usuario
        Usuario usuario = usuarioService.getUserById(reserva.getIdUsuario());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Calcular horas de la reserva
        LocalDateTime entrada = reserva.getDiaEntrada();
        LocalDateTime salida = reserva.getDiaSalida();
        long horas = Duration.between(entrada, salida).toHours();
        if (horas == 0) {
            horas = 1; // mínimo 1 hora
        }

        double cantidadADevolver = horas * 0.20;
        usuario.setMonedero(usuario.getMonedero() + cantidadADevolver);
        usuarioService.updateUsuario(usuario); // Asegúrate de que esto guarda los cambios

        // Eliminar la reserva
        boolean eliminado = reservaService.eliminarReserva(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
