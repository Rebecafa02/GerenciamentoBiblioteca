package com.biblioteca.gerenciamento.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome do curso é obrigatório")
    @Size(min = 3, max = 150, message = "O nome do curso deve ter entre 3 e 150 caracteres")
    @Column(name = "nome_curso", nullable = false)
    private String nomeCurso;
}