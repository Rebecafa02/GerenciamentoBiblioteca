package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.dto.CondicaoLivro;
import com.biblioteca.gerenciamento.domain.entity.Emprestimo;
import com.biblioteca.gerenciamento.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> create(
            @RequestBody Emprestimo emprestimo
    ) throws BadRequestException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emprestimoService.create(emprestimo));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> findAll(

            @RequestParam(required = false)
            String status,

            @RequestParam(required = false)
            String nomeAluno,

            @RequestParam(required = false)
            String tituloLivro
    ) {

        return ResponseEntity.ok(
                emprestimoService.findAll(
                        status,
                        nomeAluno,
                        tituloLivro
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                emprestimoService.findById(id)
        );
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(

            @PathVariable Integer id,

            @RequestParam
            CondicaoLivro condicaoDevolucao
    ) throws BadRequestException {

        return ResponseEntity.ok(
                emprestimoService.devolver(
                        id,
                        condicaoDevolucao
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {

        emprestimoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}