package com.example.flypark1.Repository;

import com.example.flypark1.Model.Plaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PlazaRepository extends JpaRepository<Plaza, Integer> {

    Plaza findByIdPlaza(int idPlaza);





}
