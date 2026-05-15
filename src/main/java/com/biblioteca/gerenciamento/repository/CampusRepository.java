package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampusRepository extends JpaRepository<Campus, Integer> {
    List<Campus> findByNomeContainingIgnoreCase(String nome);

    List<Campus> findByCidadeContainingIgnoreCase(String cidade);

    List<Campus> findByNomeContainingIgnoreCaseAndCidadeContainingIgnoreCase(
            String nome,
            String cidade
    );
}
