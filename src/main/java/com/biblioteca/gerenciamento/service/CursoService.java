package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.config.exceptions.ResourceNotFoundException;
import com.biblioteca.gerenciamento.domain.entity.Curso;
import com.biblioteca.gerenciamento.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Curso findById(Integer id) {

        return cursoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Curso não encontrado"));
    }

    public Curso create(Curso curso) {

        return cursoRepository.save(curso);
    }

    public Curso update(Integer id, Curso curso) {

        Curso oldCurso = findById(id);

        oldCurso.setNomeCurso(curso.getNomeCurso());

        return cursoRepository.save(oldCurso);
    }

    public void delete(Integer id) {

        Curso curso = findById(id);

        cursoRepository.delete(curso);
    }

    public List<Curso> findAll(String nomeCurso) {

        if (nomeCurso != null && !nomeCurso.isBlank()) {
            return cursoRepository
                    .findByNomeCursoContainingIgnoreCase(nomeCurso);
        }

        return cursoRepository.findAll();
    }
}