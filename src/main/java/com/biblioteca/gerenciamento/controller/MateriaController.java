package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.entity.Materia;
import com.biblioteca.gerenciamento.service.MateriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService materiaService;

    @PostMapping
    public ResponseEntity<Materia> create(@Valid @RequestBody Materia materia) {
        Materia novaMateria = materiaService.create(materia);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(novaMateria.getId()).toUri();
        return ResponseEntity.created(uri).body(novaMateria);
    }

    @GetMapping
    public ResponseEntity<List<Materia>> findAll(
            @RequestParam(required = false) String nome) {
        return ResponseEntity.ok(materiaService.findAll(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(materiaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> update(@PathVariable Integer id, @Valid @RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.update(id, materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        materiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
