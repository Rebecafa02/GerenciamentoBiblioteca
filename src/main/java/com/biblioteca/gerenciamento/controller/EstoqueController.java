package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.entity.Estoque;
import com.biblioteca.gerenciamento.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<Estoque> create(
            @RequestBody Estoque estoque
    ) throws BadRequestException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estoqueService.create(estoque));
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> findAll() {

        return ResponseEntity.ok(
                estoqueService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                estoqueService.findById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> update(
            @PathVariable Integer id,
            @RequestBody Estoque estoque
    ) throws BadRequestException {

        return ResponseEntity.ok(
                estoqueService.update(id, estoque)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estoque> updateParcial(
            @PathVariable Integer id,
            @RequestBody Estoque estoque
    ) throws BadRequestException {

        return ResponseEntity.ok(
                estoqueService.updateParcial(id, estoque)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {

        estoqueService.delete(id);

        return ResponseEntity.noContent().build();
    }
}