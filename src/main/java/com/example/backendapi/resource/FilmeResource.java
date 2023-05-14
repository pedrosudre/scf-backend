package com.example.backendapi.resource;

import com.example.backendapi.controller.FilmeController;
import com.example.backendapi.dominio.Filme;
import com.example.backendapi.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "api/filme")
public class FilmeResource {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping(value = "/list")
    public List<Filme> list() {
        return filmeRepository.findAll();
    }

    @GetMapping("/{genero}")
    public List<Filme> getByGenero(@PathVariable String genero) {
        return filmeRepository.getByGenero(genero);
    }

    @PostMapping("/create")
    public ResponseEntity<Filme> create(@RequestBody Filme filme) {
        FilmeController filmeController = new FilmeController();

        if (filmeRepository.existsByNome(filme.getNome())) {
            return new ResponseEntity("O livro já existe!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!filmeController.isFilmeValido(filme)) {
            return new ResponseEntity("Dados do filme inválidos", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        filme.setDataHoraCadastro(new Date());
        filme = filmeRepository.save(filme);
        return new ResponseEntity(filme, HttpStatus.OK);
    }

    @GetMapping("getById/{id}")
    public Optional<Filme> getById(@PathVariable(value = "id") int id) {
        return filmeRepository.findById(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<Filme> editar(@RequestBody Filme filme) {
        FilmeController filmeController = new FilmeController();
        if (!filmeController.isFilmeValido(filme)) {
            return new ResponseEntity("Nome do filme é inválido", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        filme = filmeRepository.save(filme);
        return new ResponseEntity(filme, HttpStatus.OK);
    }

    @GetMapping("/total")
    public long getTotal() {
        return filmeRepository.count();
    }

    @DeleteMapping("/remove/{id}")
    public Filme remove(@PathVariable(value = "id") int id) {
        Optional<Filme> optionalFilme = filmeRepository.findById(id);
        filmeRepository.delete(optionalFilme.get());
        return optionalFilme.get();
    }
}
