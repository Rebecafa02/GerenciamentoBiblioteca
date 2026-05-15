package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    Optional<Estoque> findByLivroId(Integer livroId);
}