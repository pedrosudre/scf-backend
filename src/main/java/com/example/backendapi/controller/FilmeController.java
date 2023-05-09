package com.example.backendapi.controller;

import com.example.backendapi.dominio.Filme;

public class FilmeController {

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