package com.example.flypark1.Controller;

import com.example.flypark1.Exception.RecordNotFoundException;
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
    @GetMapping("/getById/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable int id) {
        Usuario usuario = usuarioService.getUserById(id);
        if (usuario == null) {
            throw new RecordNotFoundException("No se ha encontrado el usuario con ID: ", id);
        }
        return ResponseEntity.ok(usuario);
    }


    @CrossOrigin
    @PostMapping("/login")
    public Usuario validateCredentials(@RequestBody Usuario loginRequest) {
        Usuario usuario = usuarioService.validateCredentials(loginRequest.getEmail(), loginRequest.getPassword());
        if (usuario != null && loginRequest.getPassword().equals(usuario.getPassword())) {
            return usuario;
        }
        return null;
    }

    @CrossOrigin
    @PutMapping("/updatePerfil")
    public ResponseEntity<Usuario> updatePerfil(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.getUserById(usuario.getId_usuario());
        if (usuarioExistente == null) {
            throw new RecordNotFoundException("No se ha encontrado el usuario con ID: ", usuario.getId_usuario());
        }

        usuarioExistente.setName(usuario.getName());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setPhone(usuario.getPhone());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setMonedero(usuario.getMonedero());
        usuarioExistente.setFoto(usuario.getFoto());

        Usuario usuarioActualizado = usuarioService.saveUsuario(usuarioExistente);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
