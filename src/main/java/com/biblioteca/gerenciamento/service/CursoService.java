package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.domain.entity.Curso;
import com.biblioteca.gerenciamento.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Optional<Curso> findById(int id) {
        return cursoRepository.findById(id);
    }

    public ResponseEntity<Curso> create(Curso curso) {
        return ResponseEntity.ok(cursoRepository.save(curso));
    }

    public ResponseEntity<Curso> update(int id, Curso curso) {
        Optional<Curso> optional = cursoRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso existing = optional.get();
        if (!Objects.equals(existing.getNome_curso(), curso.getNome_curso())) {
            existing.setNome_curso(curso.getNome_curso());
        }
        return ResponseEntity.ok(cursoRepository.save(existing));
    }

    public ResponseEntity<Void> delete(Integer id) {
        if (!cursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cursoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(cursoRepository.findAll());
    }
}
