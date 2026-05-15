package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "aluno")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Matrícula é obrigatória")
    @Column(unique = true, nullable = false)
    private String matricula;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Email(message = "Email inválido")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;
}
