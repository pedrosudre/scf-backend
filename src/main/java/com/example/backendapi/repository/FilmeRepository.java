package com.example.backendapi.repository;

import com.example.backendapi.dominio.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    @Query("SELECT f FROM Filme f WHERE f.genero = :genero")
    List<Filme> getByGenero(@Param("genero") String genero);

    boolean existsByNome(String nome);
}