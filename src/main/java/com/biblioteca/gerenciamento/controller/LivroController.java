package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.entity.Livro;
import com.biblioteca.gerenciamento.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> create(
            @Valid @RequestBody Livro livro
    ) {

        return ResponseEntity.ok(
                livroService.create(livro)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                livroService.findById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String disciplina,
            @RequestParam(required = false) String anoEscolar,
            @RequestParam(required = false) String edicao
    ) {

        return ResponseEntity.ok(
                livroService.findAll(
                        titulo,
                        disciplina,
                        anoEscolar,
                        edicao
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Livro> updateParcial(
            @PathVariable Integer id,
            @RequestBody Livro livro
    ) {

        return ResponseEntity.ok(
                livroService.updateParcial(id, livro)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {

        livroService.delete(id);

        return ResponseEntity.noContent().build();
    }
}