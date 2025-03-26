package com.example.flypark1.Controller;

import com.example.flypark1.Exception.RecordNotFoundException;
import com.example.flypark1.Model.LoginRequest;
import com.example.flypark1.Model.Usuario;
import com.example.flypark1.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Usuario usuario) {
        if (usuarioService.existUser(usuario.getEmail())) {
            return ResponseEntity.status(400).body("El usuario ya está registrado");
        }
        Usuario nuevoUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    @CrossOrigin
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.getUserByEmail(email);
        if (usuario == null) {
            throw new RecordNotFoundException("No se ha encontrado el usuario con el email: ", email);
        }
        return ResponseEntity.ok(usuario);
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = usuarioService.validateCredentials(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(isValid);
    }


}

