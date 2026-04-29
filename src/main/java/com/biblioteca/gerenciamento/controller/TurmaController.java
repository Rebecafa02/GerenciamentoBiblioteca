package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.dto.TurmaUpdateDto;
import com.biblioteca.gerenciamento.domain.entity.Turma;
import com.biblioteca.gerenciamento.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    ResponseEntity<Turma> createturma(@RequestBody Turma turma) {
        return turmaService.create(turma);
    }

    @PutMapping("/{id}")
    ResponseEntity<Turma> updateturma(@PathVariable int id, @RequestBody TurmaUpdateDto turma) {
        return turmaService.updateParcial(id, turma);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteturma(@PathVariable int id) {
        return turmaService.delete(id);
    }

    @GetMapping("/{id}")
    Optional<Turma> findturmaById(@PathVariable int id) {
        return turmaService.findById(id);
    }

    @GetMapping
    ResponseEntity<List<Turma>> findAllturma() {
        return turmaService.findAll();
    }
}
