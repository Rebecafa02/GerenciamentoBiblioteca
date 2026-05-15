package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.domain.dto.TurmaUpdateDto;
import com.biblioteca.gerenciamento.domain.entity.Turma;
import com.biblioteca.gerenciamento.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Optional<Turma> findById(int id) {
        return turmaRepository.findById(id);
    }

    public ResponseEntity<Turma> create(Turma turma) {
        return ResponseEntity.ok(turmaRepository.save(turma));
    }

    public ResponseEntity<Turma> updateParcial(int id, TurmaUpdateDto dto) {
        Optional<Turma> optional = turmaRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Turma turma = optional.get();
        if (dto.getAnoLetivo() != null) {
            turma.setAnoLetivo(dto.getAnoLetivo());
        }
        if (dto.getSerie() != null) {
            turma.setSerie(dto.getSerie());
        }
        if (dto.getIdCurso() != null) {
            turma.setIdCurso(dto.getIdCurso());
        }
        if (dto.getIdCampus() != null) {
            turma.setIdCampus(dto.getIdCampus());
        }
        return ResponseEntity.ok(turmaRepository.save(turma));
    }

    public ResponseEntity<Void> delete(Integer id) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        turmaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Turma>> findAll() {
        return ResponseEntity.ok(turmaRepository.findAll());
    }
}
