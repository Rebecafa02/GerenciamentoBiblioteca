package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.entity.Curso;
import com.biblioteca.gerenciamento.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> createCurso(
            @Valid @RequestBody Curso curso) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoService.create(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(
            @PathVariable Integer id,
            @Valid @RequestBody Curso curso) {

        return ResponseEntity.ok(cursoService.update(id, curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {

        cursoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findCursoById(@PathVariable Integer id) {

        return ResponseEntity.ok(cursoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAllCurso(
            @RequestParam(required = false) String nomeCurso) {

        return ResponseEntity.ok(
                cursoService.findAll(nomeCurso)
        );
    }
}