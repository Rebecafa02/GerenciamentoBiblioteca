package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.entity.Curso;
import com.biblioteca.gerenciamento.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    ResponseEntity<Curso> createcurso(@RequestBody Curso curso) {
        return cursoService.create(curso);
    }

    @PutMapping("/{id}")
    ResponseEntity<Curso> updatecurso(@PathVariable int id, @RequestBody Curso curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletecurso(@PathVariable int id) {
        return cursoService.delete(id);
    }

    @GetMapping("/{id}")
    Optional<Curso> findcursoById(@PathVariable int id) {
        return cursoService.findById(id);
    }

    @GetMapping
    ResponseEntity<List<Curso>> findAllcurso() {
        return cursoService.findAll();
    }
}
