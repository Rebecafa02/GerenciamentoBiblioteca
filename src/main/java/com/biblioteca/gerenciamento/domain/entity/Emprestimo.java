package com.biblioteca.gerenciamento.domain.entity;

import com.biblioteca.gerenciamento.domain.dto.CondicaoLivro;
import com.biblioteca.gerenciamento.domain.dto.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_emprestimo")
    private StatusEmprestimo statusEmprestimo;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_entrega")
    private CondicaoLivro condicaoEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_devolucao")
    private CondicaoLivro condicaoDevolucao;

    @ManyToOne
    @JoinColumn(name = "matricula_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;
}