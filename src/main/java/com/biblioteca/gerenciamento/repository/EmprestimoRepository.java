package com.biblioteca.gerenciamento.repository;


import com.biblioteca.gerenciamento.domain.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
    @Query("""
    SELECT e
    FROM Emprestimo e
    WHERE (:status IS NULL OR
           LOWER(CAST(e.statusEmprestimo AS string))
           LIKE LOWER(CONCAT('%', :status, '%')))

    AND (:nomeAluno IS NULL OR
         LOWER(e.aluno.nome)
         LIKE LOWER(CONCAT('%', :nomeAluno, '%')))

    AND (:tituloLivro IS NULL OR
         LOWER(e.livro.titulo)
         LIKE LOWER(CONCAT('%', :tituloLivro, '%')))
""")
    List<Emprestimo> findByFiltros(
            @Param("status") String status,
            @Param("nomeAluno") String nomeAluno,
            @Param("tituloLivro") String tituloLivro
    );
}