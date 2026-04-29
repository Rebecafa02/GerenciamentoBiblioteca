package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusRepository extends JpaRepository<Campus, Integer> {
}
