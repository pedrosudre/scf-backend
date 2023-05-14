package com.example.backendapi.controller;

import com.example.backendapi.dominio.Filme;
import com.example.backendapi.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

public class FilmeController {

    private FilmeRepository filmeRepository;


    public boolean isFilmeValido(Filme filme) {
        return isNomeValido(filme) && isGeneroValido(filme) && isProtagonistaValido(filme);
    }

    private boolean isNomeValido(Filme filme) {
        if ((filme.getNome().isEmpty()) || filme.getNome().length() < 1) {
            return false;
        }

        return true;
    }

    public boolean isGeneroValido(Filme filme) {
        if (filme.getGenero().isEmpty())
            return false;
        if ((filme.getGenero().length() < 3) || filme.getGenero().length() > 50)
            return false;

        return true;
    }


    public boolean isProtagonistaValido(Filme filme) {
        if ((filme.getProtagonista().isEmpty()) || filme.getProtagonista().length() < 1)
            return false;

        return true;
    }
}