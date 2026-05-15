package com.biblioteca.gerenciamento.repository;

import com.biblioteca.gerenciamento.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByMatricula(String matricula);

    @Query("""
        SELECT a
        FROM Aluno a
        WHERE
        (:nome = '' OR LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
        AND (:email = '' OR LOWER(a.email) LIKE LOWER(CONCAT('%', :email, '%')))
        AND (:matricula = '' OR LOWER(a.matricula) LIKE LOWER(CONCAT('%', :matricula, '%')))
        AND (:turmaId IS NULL OR a.turma.id = :turmaId)
    """)
    List<Aluno> findByFiltros(
            @Param("nome") String nome,
            @Param("email") String email,
            @Param("matricula") String matricula,
            @Param("turmaId") Integer turmaId
    );
}