package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findByNomeMateriaContainingIgnoreCase(String nomeMateria);
}
