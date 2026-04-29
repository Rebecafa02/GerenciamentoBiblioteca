package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Curso;
import com.biblioteca.gerenciamento.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
