package com.example.flypark1.Service;

import com.example.flypark1.Model.Usuario;
import com.example.flypark1.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    /**
     * Valida las credenciales del usuario (email y contraseña).
     * Retorna el usuario si las credenciales son correctas, sino null.
     */
    public Usuario validateCredentials(String email, String password) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario != null && password.equals(usuario.getPassword())) {
            return usuario;
        }
        return null;
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     */
    public Usuario updateUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    /**
     * Guarda un usuario nuevo en la base de datos.
     */
    public Usuario saveUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    /**
     * Verifica si ya existe un usuario con el email dado.
     */
    public boolean existUser(String email) {
        return repository.existsUsuarioByEmail(email);
    }

    /**
     * Obtiene un usuario por su email.
     */
    public Usuario getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * Obtiene un usuario por su ID.
     */
    public Usuario getUserById(int id) {
        return repository.findById(id).orElse(null);
    }
}
