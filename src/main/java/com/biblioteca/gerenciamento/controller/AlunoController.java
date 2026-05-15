package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.entity.Aluno;
import com.biblioteca.gerenciamento.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> createAluno(
            @Valid @RequestBody Aluno aluno
    ) throws BadRequestException {

        return ResponseEntity.ok(alunoService.create(aluno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(alunoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) Integer turmaId
    ) {

        return ResponseEntity.ok(
                alunoService.findAll(nome, email, matricula, turmaId)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Aluno> updateParcial(
            @PathVariable Integer id,
            @RequestBody Aluno aluno
    ) throws BadRequestException {

        return ResponseEntity.ok(
                alunoService.updateParcial(id, aluno)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {

        alunoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}