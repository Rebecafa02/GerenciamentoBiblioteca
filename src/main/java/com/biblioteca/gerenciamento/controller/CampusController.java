package com.biblioteca.gerenciamento.controller;

import com.biblioteca.gerenciamento.domain.entity.Campus;
import com.biblioteca.gerenciamento.service.CampusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campus")
@RequiredArgsConstructor
public class CampusController {

    private final CampusService campusService;

    @PostMapping
    public ResponseEntity<Campus> createCampus(@Valid @RequestBody Campus campus) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(campusService.create(campus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campus> updateCampus(
            @PathVariable Integer id,
            @Valid @RequestBody Campus campus) {

        return ResponseEntity.ok(campusService.update(id, campus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampus(@PathVariable Integer id) {
        campusService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campus> findCampusById(@PathVariable Integer id) {
        return ResponseEntity.ok(campusService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Campus>> findAllCampus(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cidade) {

        return ResponseEntity.ok(
                campusService.findAll(nome, cidade)
        );
    }
}