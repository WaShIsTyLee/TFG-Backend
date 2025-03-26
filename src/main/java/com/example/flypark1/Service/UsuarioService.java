package com.example.flypark1.Service;

import com.example.flypark1.Model.Usuario;
import com.example.flypark1.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public boolean validateCredentials(String email, String password) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario != null) {
            return password.equals(usuario.getPassword());
        }
        return false;
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

}
