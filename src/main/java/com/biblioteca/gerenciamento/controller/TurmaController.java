package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.dto.TurmaCreateDto;
import com.biblioteca.gerenciamento.domain.dto.TurmaUpdateDto;
import com.biblioteca.gerenciamento.domain.entity.Turma;
import com.biblioteca.gerenciamento.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turma")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Turma> createTurma(
            @RequestBody TurmaCreateDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(turmaService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(
            @PathVariable Integer id,
            @RequestBody TurmaUpdateDto turma) {

        return ResponseEntity.ok(
                turmaService.updateParcial(id, turma)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Integer id) {

        turmaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findTurmaById(@PathVariable Integer id) {

        return ResponseEntity.ok(turmaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAllTurma(
            @RequestParam(required = false) String serie,
            @RequestParam(required = false) Integer anoLetivo,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) String campus) {

        return ResponseEntity.ok(
                turmaService.findAllWithFilters(
                        serie,
                        anoLetivo,
                        curso,
                        campus
                )
        );
    }
}