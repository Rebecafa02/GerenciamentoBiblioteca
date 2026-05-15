package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    @Query("""
        SELECT l
        FROM Livro l
        WHERE
        (:titulo = '' OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
        AND (:disciplina = '' OR LOWER(l.disciplina) LIKE LOWER(CONCAT('%', :disciplina, '%')))
        AND (:anoEscolar = '' OR LOWER(l.anoEscolar) LIKE LOWER(CONCAT('%', :anoEscolar, '%')))
        AND (:edicao = '' OR LOWER(l.edicao) LIKE LOWER(CONCAT('%', :edicao, '%')))
    """)
    List<Livro> findByFiltros(
            @Param("titulo") String titulo,
            @Param("disciplina") String disciplina,
            @Param("anoEscolar") String anoEscolar,
            @Param("edicao") String edicao
    );
}