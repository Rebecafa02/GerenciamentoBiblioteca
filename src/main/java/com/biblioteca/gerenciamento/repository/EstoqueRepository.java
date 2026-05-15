package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    Optional<Estoque> findByLivroId(Integer livroId);
    @Query("""
        SELECT e
        FROM Estoque e
        WHERE LOWER(e.livro.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))
        AND LOWER(e.livro.disciplina) LIKE LOWER(CONCAT('%', :disciplina, '%'))
        AND LOWER(e.livro.anoEscolar) LIKE LOWER(CONCAT('%', :anoEscolar, '%'))
    """)
    List<Estoque> findByFiltros(
            @Param("titulo") String titulo,
            @Param("disciplina") String disciplina,
            @Param("anoEscolar") String anoEscolar
    );
}