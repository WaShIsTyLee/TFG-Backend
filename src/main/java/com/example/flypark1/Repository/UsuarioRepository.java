package com.example.flypark1.Repository;

import com.example.flypark1.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsUsuarioByEmail(String email);

    Usuario findByEmail(String email);
}
