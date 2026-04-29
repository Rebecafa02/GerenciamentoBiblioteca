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
        return ResponseEntity.ok(cursoRepository.findById(id)).getBody();
    }

    public ResponseEntity<Curso> create(Curso curso) {
        return ResponseEntity.ok(cursoRepository.save(curso));
    }

    public ResponseEntity<Curso> update(int id, Curso curso) {
        Curso oldCurso = cursoRepository.findById(id).get();
        if (!Objects.equals(oldCurso.getNome_curso(), curso.getNome_curso())) {
            oldCurso.setNome_curso(curso.getNome_curso());
        }
        
        return ResponseEntity.ok(cursoRepository.save(oldCurso));
    }

    public ResponseEntity<Void> delete(Integer id) {
        cursoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(cursoRepository.findAll());
    }
}
