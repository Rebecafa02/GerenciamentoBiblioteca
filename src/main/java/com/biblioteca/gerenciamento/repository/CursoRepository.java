package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    @Override
    Optional<Curso> findById(Integer integer);
    List<Curso> findByNomeCursoContainingIgnoreCase(String nomeCurso);
}
