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


    public Usuario validateCredentials(String email, String password) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario != null && password.equals(usuario.getPassword())) {
            return usuario;
        }
        return null;
    }


    public Usuario saveUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public boolean existUser(String email) {
        return repository.existsUsuarioByEmail(email);
    }

    public Usuario getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Usuario getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

}
