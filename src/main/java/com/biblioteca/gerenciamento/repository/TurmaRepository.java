package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Curso;
import com.biblioteca.gerenciamento.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    @Query("""
    SELECT t
    FROM Turma t
    WHERE (:serie IS NULL
        OR LOWER(t.serie) LIKE LOWER(CONCAT('%', :serie, '%')))

    AND (:anoLetivo IS NULL
        OR t.anoLetivo = :anoLetivo)

    AND (:curso IS NULL
        OR LOWER(t.curso.nomeCurso)
        LIKE LOWER(CONCAT('%', :curso, '%')))

    AND (:campus IS NULL
        OR LOWER(t.campus.nome)
        LIKE LOWER(CONCAT('%', :campus, '%')))
""")
    List<Turma> findByFilters(
            @Param("serie") String serie,
            @Param("anoLetivo") Integer anoLetivo,
            @Param("curso") String curso,
            @Param("campus") String campus
    );
}
